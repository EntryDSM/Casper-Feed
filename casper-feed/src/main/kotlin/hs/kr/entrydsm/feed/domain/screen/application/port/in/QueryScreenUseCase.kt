package hs.kr.entrydsm.feed.domain.screen.application.port.`in`

import hs.kr.entrydsm.feed.domain.screen.adatper.`in`.web.dto.response.QueryScreenResponse

/**
 * 화면 목록 조회를 위한 유스케이스 인터페이스입니다.
 * 화면 도메인에서 모든 화면 목록을 조회하는 역할을 담당합니다.
 */
interface QueryScreenUseCase {
    /**
     * 모든 화면 목록을 조회합니다.
     *
     * @return 화면 목록 응답
     */
    fun execute(): List<QueryScreenResponse>
}
