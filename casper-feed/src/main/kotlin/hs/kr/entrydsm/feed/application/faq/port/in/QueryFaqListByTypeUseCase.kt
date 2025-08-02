package hs.kr.entrydsm.feed.application.faq.port.`in`

import hs.kr.entrydsm.feed.adapter.`in`.faq.dto.response.FaqListResponse
import hs.kr.entrydsm.feed.model.faq.type.FaqType

/**
 * 특정 유형의 FAQ 목록 조회를 위한 유스케이스 인터페이스입니다.
 * FAQ 도메인에서 특정 유형에 해당하는 FAQ 목록을 조회하는 역할을 담당합니다.
 */
interface QueryFaqListByTypeUseCase {
    /**
     * 특정 유형의 FAQ 목록을 조회합니다.
     *
     * @param faqType 조회할 FAQ 유형
     * @return FAQ 목록 응답
     */
    fun execute(faqType: FaqType): FaqListResponse
}
