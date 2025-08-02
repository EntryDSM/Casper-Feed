package hs.kr.entrydsm.feed.adapter.out.mapper.faq

import hs.kr.entrydsm.feed.adapter.out.entity.faq.FaqJpaEntity
import hs.kr.entrydsm.feed.model.faq.Faq
import org.mapstruct.Mapper

/**
 * FAQ 도메인 모델과 JPA 엔티티 간의 변환을 담당하는 매퍼 인터페이스입니다.
 * MapStruct를 사용하여 구현체가 자동으로 생성됩니다.
 */
@Mapper(componentModel = "spring")
interface FaqMapper {
    /**
     * JPA 엔티티를 FAQ 도메인 모델로 변환합니다.
     *
     * @param entity 변환할 FaqJpaEntity 인스턴스
     * @return 변환된 FAQ 도메인 모델
     */
    fun toModel(entity: FaqJpaEntity): Faq

    /**
     * FAQ 도메인 모델을 JPA 엔티티로 변환합니다.
     *
     * @param model 변환할 FAQ 도메인 모델
     * @return 변환된 FaqJpaEntity 인스턴스
     */
    fun toEntity(model: Faq): FaqJpaEntity
}
