package hs.kr.entrydsm.feed.domain.notice.adapter.out.entity

import hs.kr.entrydsm.feed.domain.attachFile.adapter.out.entity.AttachFileJpaEntity
import hs.kr.entrydsm.feed.global.entity.BaseEntity
import hs.kr.entrydsm.feed.domain.notice.model.type.NoticeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import java.util.UUID

/**
 * 공지사항 정보를 데이터베이스에 저장하기 위한 JPA 엔티티 클래스입니다.
 *
 * @property title 공지사항 제목 (최대 100자)
 * @property content 공지사항 내용 (최대 5000자)
 * @property fileName 첨부 파일명 (선택 사항)
 * @property attachFile 공지사항에 첨부된 파일 목록 (Lazy 로딩)
 * @property adminId 공지사항을 작성한 관리자 ID (UUID)
 * @property isPinned 상단 고정 여부
 * @property type 공지사항 유형 (NoticeType ENUM)
 * @param id 엔티티의 고유 식별자 (생성 시 자동 생성됨)
 */
@Entity(name = "tbl_notice")
class NoticeJpaEntity(
    id: UUID? = null,
    @Column(name = "title", length = 100, nullable = false)
    var title: String,
    @Column(name = "content", length = 5000, nullable = false)
    var content: String,
    @Column(name = "file_name", nullable = true)
    var fileName: String? = null,
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "noticeId")
    var attachFile: List<AttachFileJpaEntity>? = emptyList(),
    @Column(name = "admin_id", nullable = false, columnDefinition = "BINARY(16)")
    var adminId: UUID,
    @Column(nullable = false)
    var isPinned: Boolean,
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    var type: NoticeType,
) : BaseEntity(id)
