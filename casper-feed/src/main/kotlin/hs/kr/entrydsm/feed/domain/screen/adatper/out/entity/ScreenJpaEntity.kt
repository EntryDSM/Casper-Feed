package hs.kr.entrydsm.feed.domain.screen.adatper.out.entity

import hs.kr.entrydsm.feed.global.entity.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import java.util.UUID

/**
 * 화면 정보를 데이터베이스에 저장하기 위한 JPA 엔티티 클래스입니다.
 *
 * @property image 화면에 표시될 이미지의 경로 또는 URL
 * @property adminId 화면을 등록한 관리자 ID (UUID)
 * @param id 엔티티의 고유 식별자 (생성 시 자동 생성됨)
 */
@Entity(name = "tbl_screen")
class ScreenJpaEntity(
    id: UUID? = null,
    var image: String,
    @Column(columnDefinition = "BINARY(16)")
    val adminId: UUID,
) : BaseEntity(id)
