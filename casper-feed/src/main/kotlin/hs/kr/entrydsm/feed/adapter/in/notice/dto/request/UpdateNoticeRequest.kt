package hs.kr.entrydsm.feed.adapter.`in`.notice.dto.request

import hs.kr.entrydsm.feed.model.notice.type.NoticeType
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

/**
 * 공지사항 수정을 위한 요청 데이터 클래스입니다.
 *
 * 이 클래스는 클라이언트로부터 전달받은 공지사항 수정 요청 데이터를 담고 있으며,
 * 유효성 검증을 위한 어노테이션들이 적용되어 있습니다.
 *
 * @property title 수정할 공지사항 제목 (필수, 최대 100자)
 * @property content 수정할 공지사항 내용 (필수, 최대 5000자)
 * @property isPinned 공지사항 상단 고정 여부 (필수)
 * @property type 공지사항 유형 (필수, GUIDE 또는 NOTICE)
 * @property fileName 수정할 공지사항의 이미지 파일명 (선택, null인 경우 기존 이미지 유지)
 * @property attachFileName 수정할 공지사항의 첨부 파일명 목록 (선택, 비어있을 수 있음)
 */
data class UpdateNoticeRequest(
    @field:NotBlank(message = "title은 null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @Size(max = 100, message = "title은 최대 100자까지 가능합니다.")
    val title: String,
    @field:NotBlank(message = "content은 null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @Size(max = 5000, message = "content은 최대 5000자까지 가능합니다.")
    val content: String,
    @field:NotNull(message = "Pinned은 null일수가 없습니다")
    val isPinned: Boolean,
    @field:NotNull(message = "type은 null일수가 없습니다")
    val type: NoticeType,
    val fileName: String? = null,
    val attachFileName: List<String>? = emptyList(),
)
