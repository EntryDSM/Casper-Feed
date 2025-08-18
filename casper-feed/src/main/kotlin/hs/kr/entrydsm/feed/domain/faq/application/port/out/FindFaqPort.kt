package hs.kr.entrydsm.feed.domain.faq.application.port.out

import hs.kr.entrydsm.feed.domain.faq.model.Faq
import hs.kr.entrydsm.feed.domain.faq.model.type.FaqType
import java.util.UUID

/**
 * FAQ 조회를 위한 포트 인터페이스입니다.
 * FAQ 도메인 객체를 다양한 방식으로 조회하는 메서드를 정의합니다.
 */
interface FindFaqPort {
    /**
     * FAQ ID로 FAQ를 조회합니다.
     *
     * @param faqId 조회할 FAQ의 고유 식별자
     * @return 조회된 FAQ 객체, 없을 경우 null
     */
    fun findByIdOrNull(faqId: UUID): Faq?

    /**
     * 특정 유형의 모든 FAQ를 조회합니다.
     *
     * @param faqType 조회할 FAQ 유형
     * @return 해당 유형의 FAQ 목록 (없을 경우 빈 목록)
     */
    fun findAllByFaqType(faqType: FaqType): List<Faq>

    /**
     * 모든 FAQ를 조회합니다.
     *
     * @return 모든 FAQ 목록 (없을 경우 빈 목록)
     */
    fun findAll(): List<Faq>

    /**
     * 생성일시 기준으로 최신 FAQ 5개를 조회합니다.
     *
     * @return 최신순으로 정렬된 FAQ 5개 목록 (5개 미만일 경우 모두 반환)
     */
    fun findTop5ByOrderByCreatedAtDesc(): List<Faq>
}
