package hs.kr.entrydsm.feed.domain.faq.adapter.out.persistence.repository

import hs.kr.entrydsm.feed.domain.faq.adapter.out.entity.FaqJpaEntity
import hs.kr.entrydsm.feed.domain.faq.model.type.FaqType
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

/**
 * FAQ 데이터에 접근하기 위한 JPA Repository 인터페이스입니다.
 * FAQ 엔티티의 CRUD 및 커스텀 쿼리 메서드를 제공합니다.
 */
interface FaqRepository : JpaRepository<FaqJpaEntity, UUID> {
    /**
     * 주어진 FAQ 유형에 해당하는 모든 FAQ 엔티티를 조회합니다.
     *
     * @param faqType 조회할 FAQ 유형
     * @return 조회된 FAQ 엔티티 목록 (없을 경우 빈 목록 반환)
     */
    fun findAllByFaqType(faqType: FaqType): List<FaqJpaEntity>

    /**
     * 최근에 생성된 상위 5개의 FAQ 엔티티를 조회합니다.
     * 생성일자(createdAt) 기준으로 내림차순 정렬됩니다.
     *
     * @return 최근 생성된 FAQ 엔티티 목록 (최대 5개, 없을 경우 빈 목록 반환)
     */
    fun findTop5ByOrderByCreatedAtDesc(): List<FaqJpaEntity>
}
