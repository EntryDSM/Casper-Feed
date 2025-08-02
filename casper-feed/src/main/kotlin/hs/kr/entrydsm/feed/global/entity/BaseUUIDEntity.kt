package hs.kr.entrydsm.feed.global.entity

import jakarta.persistence.Column
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import java.util.*

/**
 * UUID를 기본 키로 사용하는 모든 JPA 엔티티의 기본이 되는 추상 클래스입니다.
 * 이 클래스를 상속받는 엔티티는 자동으로 UUID를 ID로 가지게 됩니다.
 *
 * @property id 엔티티의 고유 식별자 (UUID)
 * @param id 생성자로 전달된 ID (null이면 자동 생성, UUID(0,0)이면 null로 처리됨)
 */
@MappedSuperclass
abstract class BaseUUIDEntity(
    id: UUID?,
) {
    /**
     * 엔티티의 고유 식별자입니다.
     * - `@GeneratedValue(generator = "uuid2")`: UUID v4를 사용하여 자동 생성
     * - `@Column(columnDefinition = "BINARY(16)")`: 데이터베이스에 BINARY(16)으로 저장
     * - `nullable = false`: null을 허용하지 않음
     * - `if (id == UUID(0, 0)) null else id`: UUID(0,0)이 전달되면 null로 처리
     */
    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(columnDefinition = "BINARY(16)", nullable = false)
    val id: UUID? = if (id == UUID(0, 0)) null else id
}
