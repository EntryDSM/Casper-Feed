package hs.kr.entrydsm.feed.application.faq.service

import hs.kr.entrydsm.feed.adapter.`in`.faq.dto.response.FaqDto
import hs.kr.entrydsm.feed.adapter.`in`.faq.dto.response.FaqListResponse
import hs.kr.entrydsm.feed.application.faq.port.`in`.QueryFaqListUseCase
import hs.kr.entrydsm.feed.application.faq.port.out.FindFaqPort
import org.springframework.stereotype.Service

/**
 * FAQ 목록 조회를 처리하는 서비스 클래스입니다.
 *
 * @property findFaqPort FAQ 조회를 위한 포트
 */
@Service
class QueryFaqListService(
    private val findFaqPort: FindFaqPort,
) : QueryFaqListUseCase {
    /**
     * 모든 FAQ 목록을 조회합니다.
     *
     * @return FAQ 목록 응답
     */
    override fun execute(): FaqListResponse {
        val faqs =
            findFaqPort.findAll().map {
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
