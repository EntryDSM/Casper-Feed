package hs.kr.entrydsm.feed.model.notice

import hs.kr.entrydsm.feed.model.attachFile.AttachFile
import hs.kr.entrydsm.feed.model.notice.type.NoticeType
import java.time.LocalDateTime
import java.util.UUID

/**
 * 공지사항 도메인 모델 클래스입니다.
 *
 * @property id 공지사항의 고유 식별자 (생성 시 자동 할당)
 * @property title 공지사항 제목
 * @property content 공지사항 내용
 * @property fileName 첨부 파일명 (선택 사항)
 * @property attachFile 첨부 파일 목록 (선택 사항)
 * @property adminId 공지사항을 작성한 관리자 ID
 * @property isPinned 상단 고정 여부
 * @property type 공지사항 유형
 * @property createdAt 공지사항 생성 일시 (기본값: 현재 시간)
 */
data class Notice(
    val id: UUID? = null,
    val title: String,
    val content: String,
    val fileName: String? = null,
    val attachFile: List<AttachFile>? = emptyList(),
    val adminId: UUID,
    val isPinned: Boolean,
    val type: NoticeType,
    val createdAt: LocalDateTime = LocalDateTime.now(),
) {
    /**
     * 공지사항 정보를 업데이트합니다.
     *
     * @param newTitle 새로운 제목
     * @param newContent 새로운 내용
     * @param newFileName 새로운 파일명 (선택 사항)
     * @param newAttachFile 새로운 첨부 파일 목록 (선택 사항)
     * @param newAdminId 수정한 관리자 ID
     * @param newIsPinned 새로운 상단 고정 여부
     * @param newType 새로운 공지사항 유형
     * @return 업데이트된 Notice 객체
     */
    fun updateNotice(
        newTitle: String,
        newContent: String,
        newFileName: String?,
        newAttachFile: List<AttachFile>? = emptyList(),
        newAdminId: UUID,
        newIsPinned: Boolean,
        newType: NoticeType,
    ): Notice {
        return copy(
            title = newTitle,
            content = newContent,
            fileName = newFileName,
            attachFile = newAttachFile,
            adminId = newAdminId,
            isPinned = newIsPinned,
            type = newType,
        )
    }
}
