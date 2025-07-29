package hs.kr.entrydsm.feed.application.notice.service

import hs.kr.entrydsm.feed.adapter.`in`.notice.dto.response.UploadNoticeImageResponse
import hs.kr.entrydsm.feed.application.notice.port.`in`.UploadNoticeImageUseCase
import hs.kr.entrydsm.feed.infrastructure.s3.PathList
import hs.kr.entrydsm.feed.infrastructure.s3.util.FileUtil
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

/**
 * 공지사항 이미지 업로드를 처리하는 서비스 클래스입니다.
 *
 * @property fileUtil 파일 업로드 유틸리티
 */
@Transactional
@Service
class UploadNoticeImageService(
    private val fileUtil: FileUtil,
) : UploadNoticeImageUseCase {
    /**
     * 공지사항에 첨부할 이미지를 업로드합니다.
     *
     * @param image 업로드할 이미지 파일
     * @return 업로드된 이미지의 URL과 파일명이 포함된 응답
     */
    override fun execute(image: MultipartFile): UploadNoticeImageResponse {
        val fileName = fileUtil.upload(image, PathList.NOTICE)
        return UploadNoticeImageResponse(fileUtil.generateObjectUrl(fileName, PathList.NOTICE), fileName)
    }
}
