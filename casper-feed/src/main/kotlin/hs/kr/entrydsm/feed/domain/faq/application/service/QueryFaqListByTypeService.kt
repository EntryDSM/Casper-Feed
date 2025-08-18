package hs.kr.entrydsm.feed.domain.faq.application.service

import hs.kr.entrydsm.feed.domain.faq.adapter.`in`.web.dto.response.FaqDto
import hs.kr.entrydsm.feed.domain.faq.adapter.`in`.web.dto.response.FaqListResponse
import hs.kr.entrydsm.feed.domain.faq.application.port.`in`.QueryFaqListByTypeUseCase
import hs.kr.entrydsm.feed.domain.faq.application.port.out.FindFaqPort
import hs.kr.entrydsm.feed.domain.faq.model.type.FaqType
import org.springframework.stereotype.Service

/**
 * 특정 유형의 FAQ 목록 조회를 처리하는 서비스 클래스입니다.
 *
 * @property findFaqPort FAQ 조회를 위한 포트
 */
@Service
class QueryFaqListByTypeService(
    private val findFaqPort: FindFaqPort,
) : QueryFaqListByTypeUseCase {
    /**
     * 지정된 유형의 FAQ 목록을 조회합니다.
     *
     * @param faqType 조회할 FAQ 유형
     * @return FAQ 목록 응답
     */
    override fun execute(faqType: FaqType): FaqListResponse {
        val faqs =
            findFaqPort.findAllByFaqType(faqType).map {
                FaqDto(
                    id = it.id!!,
                    title = it.title,
                    content = it.content,
                    createdAt = it.createdAt,
                    faqType = it.faqType,
                )
            }
        return FaqListResponse(faqs)
    }
}
