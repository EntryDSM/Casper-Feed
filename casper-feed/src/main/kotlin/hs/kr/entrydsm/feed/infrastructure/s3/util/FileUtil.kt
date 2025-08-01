package hs.kr.entrydsm.feed.infrastructure.s3.util

import com.amazonaws.HttpMethod
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import hs.kr.entrydsm.feed.infrastructure.s3.exception.BadFileExtensionException
import hs.kr.entrydsm.feed.infrastructure.s3.exception.EmptyFileException
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.util.*

/**
 * AWS S3와의 상호작용을 추상화한 유틸리티 클래스입니다.
 *
 * 이 클래스는 파일 업로드, 다운로드 URL 생성, 파일 삭제 등 S3와 관련된
 * 공통적인 작업을 처리하기 위한 유틸리티 메서드를 제공합니다.
 *
 * @property amazonS3 AWS S3 클라이언트 인스턴스
 * @property bucketName S3 버킷 이름 (application.yml에서 주입됨)
 *
 * @throws EmptyFileException 업로드할 파일이 비어있는 경우 발생
 * @throws BadFileExtensionException 허용되지 않은 파일 확장자인 경우 발생
 */
@Service
class FileUtil(
    private val amazonS3: AmazonS3,
) {
    @Value("\${cloud.aws.s3.bucket}")
    lateinit var bucketName: String

    companion object {
        const val EXP_TIME = 10000 * 60 * 2
    }

    /**
     * 파일을 S3에 업로드하고 생성된 파일명을 반환합니다.
     *
     * @param file 업로드할 파일
     * @param path S3 버킷 내 저장 경로
     * @return 생성된 랜덤 파일명 (확장자 포함)
     * @throws EmptyFileException 파일이 비어있는 경우
     * @throws BadFileExtensionException 허용되지 않은 확장자인 경우
     */
    fun upload(
        file: MultipartFile,
        path: String,
    ): String {
        val ext = verificationFile(file)

        val randomName = UUID.randomUUID().toString()
        val filename = "$randomName.$ext"
        val inputStream: InputStream = ByteArrayInputStream(file.bytes)

        val metadata =
            ObjectMetadata().apply {
                contentType =
                    when (ext) {
                        "pdf" -> MediaType.APPLICATION_PDF_VALUE
                        else -> MediaType.IMAGE_PNG_VALUE
                    }
                contentLength = file.size
                contentDisposition = "inline"
            }

        inputStream.use {
            amazonS3.putObject(
                PutObjectRequest(bucketName, "${path}$filename", it, metadata)
                    .withCannedAcl(CannedAccessControlList.AuthenticatedRead),
            )
        }

        return filename
    }

    /**
     * S3에서 지정된 파일을 삭제합니다.
     *
     * @param objectName 삭제할 파일명
     * @param path 파일이 위치한 S3 경로
     */
    fun delete(
        objectName: String,
        path: String,
    ) {
        amazonS3.deleteObject(bucketName, path + objectName)
    }

    /**
     * S3에 저장된 파일에 접근할 수 있는 임시 URL을 생성합니다.
     *
     * @param fileName 접근할 파일명
     * @param path 파일이 위치한 S3 경로
     * @return 임시 접근 URL
     */
    fun generateObjectUrl(
        fileName: String,
        path: String,
    ): String {
        val expiration =
            Date().apply {
                time += EXP_TIME
            }
        return amazonS3.generatePresignedUrl(
            GeneratePresignedUrlRequest(
                bucketName,
                "${path}$fileName",
            ).withMethod(HttpMethod.GET).withExpiration(expiration),
        ).toString()
    }

    /**
     * 업로드할 파일의 유효성을 검증하고 파일 확장자를 반환합니다.
     *
     * @param file 검증할 파일
     * @return 소문자로 변환된 파일 확장자
     * @throws EmptyFileException 파일이 비어있는 경우
     * @throws BadFileExtensionException 허용되지 않은 확장자인 경우
     */
    private fun verificationFile(file: MultipartFile): String {
        if (file.isEmpty || file.originalFilename == null) throw EmptyFileException
        val originalFilename = file.originalFilename!!
        val ext = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).lowercase(Locale.getDefault())

        if (!(
                ext == "jpg" || ext == "jpeg" ||
                    ext == "png" || ext == "heic" ||
                    ext == "hwp" || ext == "pptx" ||
                    ext == "pdf" || ext == "xls" ||
                    ext == "xlsx"
            )
        ) {
            throw BadFileExtensionException
        }

        return ext
    }
}
