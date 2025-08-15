package hs.kr.entrydsm.feed.application.faq.service

import hs.kr.entrydsm.feed.adapter.`in`.faq.dto.response.FaqTitleResponse
import hs.kr.entrydsm.feed.application.faq.port.`in`.QueryTopFaqUseCase
import hs.kr.entrydsm.feed.application.faq.port.out.FindFaqPort
import org.springframework.stereotype.Service

/**
 * 상위 FAQ 조회를 처리하는 서비스 클래스입니다.
 * 최근에 생성된 FAQ 중 상위 5개를 조회합니다.
 *
 * @property findFaqPort FAQ 조회를 위한 포트
 */
@Service
class QueryTopFaqService(
    private val findFaqPort: FindFaqPort,
) : QueryTopFaqUseCase {
    /**
     * 최근에 생성된 상위 5개의 FAQ 제목 목록을 조회합니다.
     *
     * @return FAQ 제목 목록 (최대 5개)
     */
    override fun execute(): List<FaqTitleResponse> {
        return findFaqPort.findTop5ByOrderByCreatedAtDesc().map {
            FaqTitleResponse(
                it.id!!,
                it.title,
                it.content,
            )
        }
    }
}
