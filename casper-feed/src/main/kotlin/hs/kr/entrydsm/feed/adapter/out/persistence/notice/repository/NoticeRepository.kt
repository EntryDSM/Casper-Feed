package hs.kr.entrydsm.feed.adapter.out.persistence.notice.repository

import hs.kr.entrydsm.feed.adapter.out.entity.notice.NoticeJpaEntity
import hs.kr.entrydsm.feed.model.notice.type.NoticeType
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

/**
 * 공지사항 데이터에 접근하기 위한 JPA Repository 인터페이스입니다.
 * 공지사항 엔티티의 CRUD 및 커스텀 쿼리 메서드를 제공합니다.
 */
interface NoticeRepository : JpaRepository<NoticeJpaEntity, UUID> {
    /**
     * 주어진 유형에 해당하는 모든 공지사항 엔티티를 조회합니다.
     *
     * @param type 조회할 공지사항 유형
     * @return 조회된 공지사항 엔티티 목록 (없을 경우 빈 목록 반환)
     */
    fun findAllByType(type: NoticeType): List<NoticeJpaEntity>
}
