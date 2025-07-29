package hs.kr.entrydsm.feed.application.notice.port.`in`

import hs.kr.entrydsm.feed.adapter.`in`.notice.dto.response.QueryNoticeTitleResponse

/**
 * 공지사항 제목 조회를 위한 유스케이스 인터페이스입니다.
 * 공지사항 도메인에서 공지사항의 제목 목록을 조회하는 역할을 담당합니다.
 */
interface QueryNoticeTitleUseCase {
    /**
     * 공지사항 제목 목록을 조회합니다.
     *
     * @return 공지사항 제목 목록 응답
     */
    fun execute(): List<QueryNoticeTitleResponse>
}
