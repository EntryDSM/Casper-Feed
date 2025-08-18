package hs.kr.entrydsm.feed.domain.faq.application.port.`in`

import hs.kr.entrydsm.feed.domain.faq.adapter.`in`.web.dto.response.FaqTitleResponse

/**
 * 상위 FAQ 조회를 위한 유스케이스 인터페이스입니다.
 * FAQ 도메인에서 상위에 노출될 FAQ 목록을 조회하는 역할을 담당합니다.
 */
interface QueryTopFaqUseCase {
    /**
     * 상위 FAQ 목록을 조회합니다.
     *
     * @return FAQ 제목 목록 응답
     */
    fun execute(): List<FaqTitleResponse>
}
