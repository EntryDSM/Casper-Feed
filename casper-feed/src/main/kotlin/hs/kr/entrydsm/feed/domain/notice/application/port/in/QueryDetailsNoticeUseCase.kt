package hs.kr.entrydsm.feed.domain.notice.application.port.`in`

import hs.kr.entrydsm.feed.domain.notice.adapter.`in`.web.dto.response.QueryDetailsNoticeResponse
import java.util.UUID

/**
 * 공지사항 상세 조회를 위한 유스케이스 인터페이스입니다.
 * 공지사항 도메인에서 특정 공지사항의 상세 정보를 조회하는 역할을 담당합니다.
 */
interface QueryDetailsNoticeUseCase {
    /**
     * 특정 공지사항의 상세 정보를 조회합니다.
     *
     * @param noticeId 조회할 공지사항의 고유 식별자
     * @return 공지사항 상세 정보 응답
     */
    fun execute(noticeId: UUID): QueryDetailsNoticeResponse
}
