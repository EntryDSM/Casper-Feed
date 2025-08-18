package hs.kr.entrydsm.feed.domain.notice.application.port.`in`

import hs.kr.entrydsm.feed.domain.notice.adapter.`in`.web.dto.response.QueryListNoticeResponse
import hs.kr.entrydsm.feed.domain.notice.model.type.NoticeType

/**
 * 유형별 공지사항 목록 조회를 위한 유스케이스 인터페이스입니다.
 * 공지사항 도메인에서 특정 유형의 공지사항 목록을 조회하는 역할을 담당합니다.
 */
interface QueryNoticeListByTypeUseCase {
    /**
     * 특정 유형의 공지사항 목록을 조회합니다.
     *
     * @param noticeType 조회할 공지사항 유형 (null인 경우 모든 유형 포함)
     * @return 공지사항 목록 응답
     */
    fun execute(noticeType: NoticeType?): QueryListNoticeResponse
}
