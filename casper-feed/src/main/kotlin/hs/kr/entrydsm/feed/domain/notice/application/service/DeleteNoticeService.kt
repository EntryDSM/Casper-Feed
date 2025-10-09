package hs.kr.entrydsm.feed.domain.notice.application.service

import hs.kr.entrydsm.feed.domain.notice.exception.NoticeNotFoundException
import hs.kr.entrydsm.feed.domain.notice.application.port.`in`.DeleteNoticeUseCase
import hs.kr.entrydsm.feed.domain.notice.application.port.out.DeleteNoticePort
import hs.kr.entrydsm.feed.domain.notice.application.port.out.FindNoticePort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

/**
 * 공지사항 삭제를 처리하는 서비스 클래스입니다.
 *
 * @property findNoticePort 공지사항 조회를 위한 포트
 * @property deleteNoticePort 공지사항 삭제를 위한 포트
 */
@Service
class DeleteNoticeService(
    private val findNoticePort: FindNoticePort,
    private val deleteNoticePort: DeleteNoticePort,
) : DeleteNoticeUseCase {
    /**
     * 지정된 ID의 공지사항을 삭제합니다.
     *
     * @param noticeId 삭제할 공지사항의 고유 식별자
     * @throws NoticeNotFoundException 지정된 ID의 공지사항을 찾을 수 없는 경우
     */
    @Transactional
    override fun execute(noticeId: UUID) {
        val notice = findNoticePort.findByIdOrNull(noticeId) ?: throw NoticeNotFoundException
        deleteNoticePort.deleteNotice(notice)
    }
}
