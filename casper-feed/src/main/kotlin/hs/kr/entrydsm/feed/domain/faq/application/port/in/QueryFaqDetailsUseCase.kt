package hs.kr.entrydsm.feed.domain.faq.application.port.`in`

import hs.kr.entrydsm.feed.domain.faq.adapter.`in`.web.dto.response.FaqDetailsResponse
import java.util.UUID

/**
 * FAQ 상세 정보 조회를 위한 유스케이스 인터페이스입니다.
 * FAQ 도메인에서 특정 FAQ의 상세 정보를 조회하는 역할을 담당합니다.
 */
interface QueryFaqDetailsUseCase {
    /**
     * 특정 FAQ의 상세 정보를 조회합니다.
     *
     * @param faqId 조회할 FAQ의 고유 식별자
     * @return FAQ 상세 정보 응답
     */
    fun execute(faqId: UUID): FaqDetailsResponse
}
