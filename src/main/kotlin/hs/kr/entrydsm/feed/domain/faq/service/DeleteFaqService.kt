package hs.kr.entrydsm.feed.domain.faq.service

import hs.kr.entrydsm.feed.domain.faq.domain.repository.FaqRepository
import hs.kr.entrydsm.feed.domain.faq.exception.FaqNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class DeleteFaqService(
    private val faqRepository: FaqRepository
) {
    @Transactional
    fun execute(faqId: UUID) {
        val faq = faqRepository.findByIdOrNull(faqId) ?: throw FaqNotFoundException
        faqRepository.delete(faq)
    }
}
