package hs.kr.entrydsm.feed.domain.notice.application.port.`in`

import hs.kr.entrydsm.feed.domain.notice.adapter.`in`.web.dto.request.UpdateNoticeRequest
import org.springframework.http.ResponseEntity
import java.util.UUID

/**
 * 공지사항 수정을 위한 유스케이스 인터페이스입니다.
 * 공지사항 도메인에서 기존 공지사항을 수정하는 역할을 담당합니다.
 */
interface UpdateNoticeUseCase {
    /**
     * 특정 공지사항을 수정합니다.
     *
     * @param noticeId 수정할 공지사항의 고유 식별자
     * @param request 공지사항 수정 요청 데이터
     * @return 수정 결과 응답 엔티티
     */
    fun execute(
        noticeId: UUID,
        request: UpdateNoticeRequest,
    ): ResponseEntity<String>
}
