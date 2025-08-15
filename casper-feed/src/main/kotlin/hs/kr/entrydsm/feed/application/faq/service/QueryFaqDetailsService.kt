package hs.kr.entrydsm.feed.application.faq.service

import hs.kr.entrydsm.feed.adapter.`in`.faq.dto.response.FaqDetailsResponse
import hs.kr.entrydsm.feed.application.faq.exception.FaqNotFoundException
import hs.kr.entrydsm.feed.application.faq.port.`in`.QueryFaqDetailsUseCase
import hs.kr.entrydsm.feed.application.faq.port.out.FindFaqPort
import org.springframework.stereotype.Service
import java.util.UUID

/**
 * FAQ 상세 조회를 처리하는 서비스 클래스입니다.
 *
 * @property findFaqPort FAQ 조회를 위한 포트
 */
@Service
class QueryFaqDetailsService(
    private val findFaqPort: FindFaqPort,
) : QueryFaqDetailsUseCase {
    /**
     * 지정된 ID의 FAQ 상세 정보를 조회합니다.
     *
     * @param faqId 조회할 FAQ의 고유 식별자
     * @return FAQ 상세 정보 응답
     * @throws hs.kr.entrydsm.feed.application.faq.exception.FaqNotFoundException 지정된 ID의 FAQ를 찾을 수 없는 경우
     */
    override fun execute(faqId: UUID): FaqDetailsResponse {
        val faq = findFaqPort.findByIdOrNull(faqId) ?: throw FaqNotFoundException
        return FaqDetailsResponse(
            title = faq.title,
            content = faq.content,
            createdAt = faq.createdAt,
            faqType = faq.faqType,
        )
    }
}
