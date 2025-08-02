package hs.kr.entrydsm.feed.adapter.out.entity.faq

import hs.kr.entrydsm.feed.global.entity.BaseEntity
import hs.kr.entrydsm.feed.model.faq.type.FaqType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import java.util.UUID

/**
 * FAQ 정보를 데이터베이스에 저장하기 위한 JPA 엔티티 클래스입니다.
 *
 * @property title FAQ 제목 (최대 100자)
 * @property content FAQ 내용 (최대 5000자)
 * @property faqType FAQ 유형 (FaqType ENUM)
 * @property adminId FAQ를 작성한 관리자 ID (UUID)
 * @param id 엔티티의 고유 식별자 (생성 시 자동 생성됨)
 */
@Entity(name = "tbl_faq")
class FaqJpaEntity(
    id: UUID? = null,
    @Column(name = "title", length = 100, nullable = false)
    var title: String,
    @Column(name = "content", length = 5000, nullable = false)
    var content: String,
    @Enumerated(EnumType.STRING)
    var faqType: FaqType,
    @Column(name = "admin_id", columnDefinition = "BINARY(16)", nullable = false)
    var adminId: UUID,
) : BaseEntity(id)
