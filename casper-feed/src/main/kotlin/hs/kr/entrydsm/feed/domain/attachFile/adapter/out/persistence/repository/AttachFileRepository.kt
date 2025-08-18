package hs.kr.entrydsm.feed.domain.attachFile.adapter.out.persistence.repository

import hs.kr.entrydsm.feed.domain.attachFile.adapter.out.entity.AttachFileJpaEntity
import org.springframework.data.jpa.repository.JpaRepository

/**
 * 첨부 파일 데이터에 접근하기 위한 JPA Repository 인터페이스입니다.
 * 첨부 파일 엔티티의 CRUD 및 커스텀 쿼리 메서드를 제공합니다.
 */
interface AttachFileRepository : JpaRepository<AttachFileJpaEntity, String> {
    /**
     * 원본 파일명으로 첨부 파일 엔티티를 조회합니다.
     *
     * @param attachFileName 조회할 원본 파일명
     * @return 조회된 첨부 파일 엔티티 목록 (없을 경우 null 반환)
     */
    fun findByOriginalAttachFileName(attachFileName: String): List<AttachFileJpaEntity>?

    /**
     * 원본 파일명에 해당하는 모든 첨부 파일 엔티티를 삭제합니다.
     *
     * @param attachFileName 삭제할 원본 파일명
     */
    fun deleteByOriginalAttachFileName(attachFileName: String)

    /**
     * 주어진 원본 파일명을 가진 첨부 파일이 존재하는지 확인합니다.
     *
     * @param attachFileName 확인할 원본 파일명
     * @return 파일이 존재하면 true, 그렇지 않으면 false
     */
    fun existsByOriginalAttachFileName(attachFileName: String): Boolean
}
