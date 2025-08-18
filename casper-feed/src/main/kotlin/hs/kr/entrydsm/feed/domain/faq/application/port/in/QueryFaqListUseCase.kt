package hs.kr.entrydsm.feed.domain.faq.application.port.`in`

import hs.kr.entrydsm.feed.domain.faq.adapter.`in`.web.dto.response.FaqListResponse

/**
 * FAQ 목록 조회를 위한 유스케이스 인터페이스입니다.
 * FAQ 도메인에서 모든 FAQ 목록을 조회하는 역할을 담당합니다.
 */
interface QueryFaqListUseCase {
    /**
     * 모든 FAQ 목록을 조회합니다.
     *
     * @return FAQ 목록 응답
     */
    fun execute(): FaqListResponse
}
