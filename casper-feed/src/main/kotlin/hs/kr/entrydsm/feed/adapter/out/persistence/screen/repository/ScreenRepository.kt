package hs.kr.entrydsm.feed.adapter.out.persistence.screen.repository

import hs.kr.entrydsm.feed.adapter.out.entity.screen.ScreenJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

/**
 * 화면(Screen) 데이터에 접근하기 위한 JPA Repository 인터페이스입니다.
 * 화면 엔티티의 CRUD 작업을 위한 기본 메서드를 상속받아 사용합니다.
 */
interface ScreenRepository : JpaRepository<ScreenJpaEntity, UUID>
