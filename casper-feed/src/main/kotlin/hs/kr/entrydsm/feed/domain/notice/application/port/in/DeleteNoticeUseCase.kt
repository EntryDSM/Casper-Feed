package hs.kr.entrydsm.feed.domain.notice.application.port.`in`

import java.util.UUID

/**
 * 공지사항 삭제를 위한 유스케이스 인터페이스입니다.
 * 공지사항 도메인에서 특정 공지사항을 삭제하는 역할을 담당합니다.
 */
interface DeleteNoticeUseCase {
    /**
     * 특정 공지사항을 삭제합니다.
     *
     * @param noticeId 삭제할 공지사항의 고유 식별자
     */
    fun execute(noticeId: UUID)
}
