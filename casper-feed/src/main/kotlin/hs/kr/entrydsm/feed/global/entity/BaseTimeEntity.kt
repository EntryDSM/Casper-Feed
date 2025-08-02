package hs.kr.entrydsm.feed.global.entity

import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

/**
 * 엔티티의 생성 시간과 수정 시간을 자동으로 관리하는 추상 기본 클래스입니다.
 * 이 클래스를 상속받은 엔티티는 자동으로 생성 시간과 수정 시간이 추적됩니다.
 *
 * @property createdAt 엔티티가 생성된 시간 (자동 설정됨)
 * @property modifiedAt 엔티티가 마지막으로 수정된 시간 (자동 갱신됨)
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseTimeEntity(
    @CreatedDate
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @LastModifiedDate
    val modifiedAt: LocalDateTime = LocalDateTime.now(),
)
