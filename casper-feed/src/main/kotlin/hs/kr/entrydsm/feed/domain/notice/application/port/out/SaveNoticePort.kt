package hs.kr.entrydsm.feed.domain.notice.application.port.out

import hs.kr.entrydsm.feed.domain.notice.model.Notice

/**
 * 공지사항 저장을 위한 포트 인터페이스입니다.
 * 공지사항 도메인 객체를 저장하거나 업데이트하는 메서드를 정의합니다.
 */
interface SaveNoticePort {
    /**
     * 공지사항을 저장하거나 업데이트합니다.
     *
     * @param notice 저장하거나 업데이트할 공지사항 도메인 객체
     */
    fun saveNotice(notice: Notice)
}
