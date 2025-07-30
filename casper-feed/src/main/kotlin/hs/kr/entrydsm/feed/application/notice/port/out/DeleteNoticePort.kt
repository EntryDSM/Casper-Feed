package hs.kr.entrydsm.feed.application.notice.port.out

import hs.kr.entrydsm.feed.model.notice.Notice

/**
 * 공지사항 삭제를 위한 포트 인터페이스입니다.
 * 공지사항 도메인 객체를 삭제하는 메서드를 정의합니다.
 */
interface DeleteNoticePort {
    /**
     * 공지사항을 삭제합니다.
     *
     * @param notice 삭제할 공지사항 도메인 객체
     */
    fun deleteNotice(notice: Notice)
}
