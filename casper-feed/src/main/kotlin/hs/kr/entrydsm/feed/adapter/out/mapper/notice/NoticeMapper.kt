package hs.kr.entrydsm.feed.adapter.out.mapper.notice

import hs.kr.entrydsm.feed.adapter.out.entity.notice.NoticeJpaEntity
import hs.kr.entrydsm.feed.model.notice.Notice
import org.mapstruct.Mapper

/**
 * Notice 도메인 모델과 JPA 엔티티 간의 변환을 담당하는 매퍼 인터페이스입니다.
 * MapStruct를 사용하여 구현체가 자동으로 생성됩니다.
 */
@Mapper(componentModel = "spring")
interface NoticeMapper {
    /**
     * Notice 도메인 모델을 JPA 엔티티로 변환합니다.
     *
     * @param model 변환할 Notice 도메인 모델
     * @return 변환된 NoticeJpaEntity 인스턴스
     */
    fun toEntity(model: Notice): NoticeJpaEntity

    /**
     * JPA 엔티티를 Notice 도메인 모델로 변환합니다.
     *
     * @param entity 변환할 NoticeJpaEntity 인스턴스
     * @return 변환된 Notice 도메인 모델
     */
    fun toModel(entity: NoticeJpaEntity): Notice
}
