package hs.kr.entrydsm.feed.domain.faq.application.service

import hs.kr.entrydsm.feed.domain.faq.application.port.`in`.DeleteFaqUseCase
import hs.kr.entrydsm.feed.domain.faq.application.port.out.DeleteFaqPort
import hs.kr.entrydsm.feed.domain.faq.application.port.out.FindFaqPort
import hs.kr.entrydsm.feed.domain.faq.exception.FaqNotFoundException
import org.springframework.stereotype.Service
import java.util.UUID

/**
 * FAQ 삭제를 처리하는 서비스 클래스입니다.
 *
 * @property findFaqPort FAQ 조회를 위한 포트
 * @property deleteFaqPort FAQ 삭제를 위한 포트
 */
@Service
class DeleteFaqService(
    private val findFaqPort: FindFaqPort,
    private val deleteFaqPort: DeleteFaqPort,
) : DeleteFaqUseCase {
    /**
     * 지정된 ID의 FAQ를 삭제합니다.
     *
     * @param faqId 삭제할 FAQ의 고유 식별자
     * @throws hs.kr.entrydsm.feed.domain.faq.exception.FaqNotFoundException ID의 FAQ를 찾을 수 없는 경우
     */
    override fun execute(faqId: UUID) {
        val faq = findFaqPort.findByIdOrNull(faqId) ?: throw FaqNotFoundException
        deleteFaqPort.deleteFaq(faq)
    }
}
