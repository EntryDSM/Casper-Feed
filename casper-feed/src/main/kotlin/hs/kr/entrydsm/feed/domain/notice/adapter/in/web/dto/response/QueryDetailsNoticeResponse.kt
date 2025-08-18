package hs.kr.entrydsm.feed.domain.notice.adapter.`in`.web.dto.response

import hs.kr.entrydsm.feed.domain.notice.model.type.NoticeType
import java.time.LocalDateTime

/**
 * 공지사항 상세 조회 응답을 위한 데이터 클래스입니다.
 *
 * @property title 공지사항 제목
 * @property content 공지사항 내용
 * @property createdAt 공지사항 생성 일시
 * @property type 공지사항 유형
 * @property imageURL 공지사항에 첨부된 이미지 URL (없을 수 있음)
 * @property imageName 공지사항에 첨부된 이미지 파일명 (없을 수 있음)
 * @property attachFiles 공지사항에 첨부된 파일 목록 (없을 수 있음)
 * @property isPinned 공지사항 고정 여부
 */
data class QueryDetailsNoticeResponse(
    val title: String,
    val content: String,
    val createdAt: LocalDateTime,
    val type: NoticeType,
    val imageURL: String?,
    val imageName: String?,
    val attachFiles: List<AttachFileElement> = emptyList(),
    val isPinned: Boolean,
)

/**
 * 공지사항에 첨부된 파일 정보를 나타내는 데이터 클래스입니다.
 *
 * @property attachFileUrl 첨부 파일 다운로드 URL
 * @property attachFileName 첨부 파일 원본 이름
 */
data class AttachFileElement(
    val attachFileUrl: String,
    val attachFileName: String,
)
