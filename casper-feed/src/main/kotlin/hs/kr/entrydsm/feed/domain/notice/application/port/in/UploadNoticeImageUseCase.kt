package hs.kr.entrydsm.feed.domain.notice.application.port.`in`

import hs.kr.entrydsm.feed.domain.notice.adapter.`in`.web.dto.response.UploadNoticeImageResponse
import org.springframework.web.multipart.MultipartFile

/**
 * 공지사항 이미지 업로드를 위한 유스케이스 인터페이스입니다.
 * 공지사항 도메인에서 공지사항에 첨부할 이미지를 업로드하는 역할을 담당합니다.
 */
interface UploadNoticeImageUseCase {
    /**
     * 공지사항에 첨부할 이미지를 업로드합니다.
     *
     * @param image 업로드할 이미지 파일
     * @return 이미지 업로드 결과 응답
     */
    fun execute(image: MultipartFile): UploadNoticeImageResponse
}
