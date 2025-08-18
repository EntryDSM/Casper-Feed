package hs.kr.entrydsm.feed.domain.notice.application.port.out

import hs.kr.entrydsm.feed.domain.notice.model.Notice
import hs.kr.entrydsm.feed.domain.notice.model.type.NoticeType
import java.util.UUID

/**
 * 공지사항 조회를 위한 포트 인터페이스입니다.
 * 공지사항 도메인 객체를 다양한 방식으로 조회하는 메서드를 정의합니다.
 */
interface FindNoticePort {
    /**
     * 공지사항 ID로 공지사항을 조회합니다.
     *
     * @param noticeId 조회할 공지사항의 고유 식별자
     * @return 조회된 공지사항 객체, 없을 경우 null
     */
    fun findByIdOrNull(noticeId: UUID): Notice?

    /**
     * 특정 유형의 모든 공지사항을 조회합니다.
     *
     * @param noticeType 조회할 공지사항 유형
     * @return 해당 유형의 공지사항 목록 (없을 경우 빈 목록)
     */
    fun findAllByType(noticeType: NoticeType): List<Notice>

    /**
     * 모든 공지사항을 조회합니다.
     *
     * @return 모든 공지사항 목록 (없을 경우 빈 목록)
     */
    fun findAll(): List<Notice>
}
