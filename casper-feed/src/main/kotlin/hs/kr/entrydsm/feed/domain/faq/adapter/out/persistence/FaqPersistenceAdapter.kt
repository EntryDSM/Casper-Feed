package hs.kr.entrydsm.feed.domain.faq.adapter.out.persistence

import hs.kr.entrydsm.feed.domain.faq.adapter.out.mapper.FaqMapper
import hs.kr.entrydsm.feed.domain.faq.adapter.out.persistence.repository.FaqRepository
import hs.kr.entrydsm.feed.domain.faq.application.port.out.FaqPort
import hs.kr.entrydsm.feed.domain.faq.model.Faq
import hs.kr.entrydsm.feed.domain.faq.model.type.FaqType
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.util.UUID

/**
 * FAQ 도메인과 데이터베이스 간의 상호작용을 담당하는 어댑터 클래스입니다.
 *
 * @property faqRepository FAQ 엔티티를 데이터베이스에서 조작하기 위한 리포지토리
 * @property faqMapper FAQ 도메인 객체와 엔티티 간의 변환을 담당하는 매퍼
 */
@Component
class FaqPersistenceAdapter(
    private val faqRepository: FaqRepository,
    private val faqMapper: FaqMapper,
) : FaqPort {

    /**
     * FAQ를 저장하거나 업데이트합니다.
     *
     * @param faq 저장할 FAQ 도메인 모델
     * @return 저장된 FAQ 도메인 모델
     */
    override fun saveFaq(faq: Faq): Faq = faqMapper.toModel(faqRepository.save(faqMapper.toEntity(faq)))

    /**
     * FAQ를 삭제합니다.
     *
     * @param faq 삭제할 FAQ 도메인 모델
     */
    override fun deleteFaq(faq: Faq) {
        faqRepository.delete(faqMapper.toEntity(faq))
    }

    /**
     * ID로 FAQ를 조회합니다.
     *
     * @param faqId 조회할 FAQ의 고유 식별자
     * @return 조회된 FAQ 도메인 모델, 존재하지 않을 경우 null 반환
     */
    override fun findByIdOrNull(faqId: UUID): Faq? = 
        faqRepository.findByIdOrNull(faqId)?.let { faqMapper.toModel(it) }

    /**
     * 특정 유형의 FAQ 목록을 조회합니다.
     *
     * @param faqType 조회할 FAQ 유형
     * @return 조회된 FAQ 도메인 모델 목록
     */
    override fun findAllByFaqType(faqType: FaqType): List<Faq> {
        return faqRepository.findAllByFaqType(faqType).map { faqMapper.toModel(it) }
    }

    /**
     * 모든 FAQ 목록을 조회합니다.
     *
     * @return 모든 FAQ 도메인 모델 목록
     */
    override fun findAll(): List<Faq> {
        return faqRepository.findAll().map { faqMapper.toModel(it) }
    }

    /**
     * 최근에 생성된 상위 5개의 FAQ를 조회합니다.
     * 생성일자 기준 내림차순으로 정렬됩니다.
     *
     * @return 최근 FAQ 5개의 도메인 모델 목록
     */
    override fun findTop5ByOrderByCreatedAtDesc(): List<Faq> {
        return faqRepository.findTop5ByOrderByCreatedAtDesc().map { faqMapper.toModel(it) }
    }
}
