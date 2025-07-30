package hs.kr.entrydsm.feed.application.attachFile.service

import hs.kr.entrydsm.feed.adapter.`in`.attachFile.dto.response.CreateAttachFileResponse
import hs.kr.entrydsm.feed.application.attachFile.port.`in`.CreateAttachFileUseCase
import hs.kr.entrydsm.feed.application.attachFile.port.out.DeleteAttachFilePort
import hs.kr.entrydsm.feed.application.attachFile.port.out.ExistsAttachFilePort
import hs.kr.entrydsm.feed.application.attachFile.port.out.SaveAttachFilePort
import hs.kr.entrydsm.feed.infrastructure.s3.PathList
import hs.kr.entrydsm.feed.infrastructure.s3.util.FileUtil
import hs.kr.entrydsm.feed.model.attachFile.AttachFile
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

/**
 * 첨부 파일 도메인에 대한 비즈니스 로직을 처리하는 서비스 클래스입니다.
 *
 * 이 클래스는 첨부 파일과 관련된 모든 비즈니스 로직을 캡슐화하며,
 * 파일 업로드, 조회, 삭제 등의 기능을 제공합니다.
 *
 * @property fileUtil 파일 업로드/다운로드와 관련된 유틸리티
 * @property existsAttachFilePort 첨부 파일 존재 여부 확인을 위한 포트
 * @property deleteAttachFilePort 첨부 파일 삭제를 위한 포트
 * @property saveAttachFilePort 첨부 파일 저장을 위한 포트
 */
@Service
class CreateAttachFileService(
    private val fileUtil: FileUtil,
    private val existsAttachFilePort: ExistsAttachFilePort,
    private val deleteAttachFilePort: DeleteAttachFilePort,
    private val saveAttachFilePort: SaveAttachFilePort,
) : CreateAttachFileUseCase {
    /**
     * 첨부 파일을 업로드하고 저장된 파일 정보를 반환합니다.
     *
     * @param attachFile 업로드할 파일 목록 (MultipartFile)
     * @return 업로드된 파일 정보 목록 (CreateAttachFileResponse 리스트)
     * 
     * @throws Exception 파일 업로드 중 오류가 발생한 경우
     * 
     * 이 메서드는 다음 작업을 수행합니다:
     * 1. 이미 동일한 이름의 파일이 존재하면 삭제합니다.
     * 2. 파일을 S3에 업로드합니다.
     * 3. 업로드된 파일 정보를 데이터베이스에 저장합니다.
     * 4. 업로드된 파일에 접근할 수 있는 URL을 생성하여 응답을 반환합니다.
     */
    override fun execute(attachFile: List<MultipartFile>): List<CreateAttachFileResponse> {
        val attachFileResponses = mutableListOf<CreateAttachFileResponse>()

        attachFile.forEach { file ->
            if (existsAttachFilePort.existsByOriginalAttachFileName(file.originalFilename!!)) {
                deleteAttachFilePort.deleteByOriginalAttachFileName(file.originalFilename!!)
            }
            val uploadedFilename = fileUtil.upload(file, PathList.ATTACH_FILE)
            val attachFile =
                AttachFile(
                    uploadedFileName = uploadedFilename,
                    originalAttachFileName = file.originalFilename!!,
                )
            saveAttachFilePort.save(attachFile)
            val url = fileUtil.generateObjectUrl(uploadedFilename, PathList.ATTACH_FILE)
            attachFileResponses.add(CreateAttachFileResponse(file.originalFilename!!, url))
        }
        return attachFileResponses
    }
}
