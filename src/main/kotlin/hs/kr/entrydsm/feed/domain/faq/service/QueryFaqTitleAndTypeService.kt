package hs.kr.entrydsm.feed.domain.faq.service

import hs.kr.entrydsm.feed.domain.faq.domain.repository.FaqRepository
import hs.kr.entrydsm.feed.domain.faq.presentation.dto.response.FaqTitleAndTypeResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueryFaqTitleAndTypeService(
    private val faqRepository: FaqRepository
) {

    @Transactional(readOnly = true)
    fun execute() =
        faqRepository.findAll()
            .map {
                    it ->
                FaqTitleAndTypeResponse(
                    it.id!!,
                    it.faqType,
                    it.title
                )
            }
}
