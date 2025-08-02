package hs.kr.entrydsm.feed.global.entity

import jakarta.persistence.Column
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import java.util.*

/**
 * 모든 엔티티 클래스의 기본이 되는 추상 클래스입니다.
 * 이 클래스를 상속받는 엔티티는 자동으로 생성 시간과 수정 시간을 추적할 수 있습니다.
 *
 * @property id 엔티티의 고유 식별자 (UUID)
 * @see BaseTimeEntity 생성 시간과 수정 시간을 관리하는 부모 클래스
 */
@MappedSuperclass
abstract class BaseEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(
        columnDefinition = "BINARY(16)",
        nullable = false,
    )
    val id: UUID?,
) : BaseTimeEntity()
