package hs.kr.entrydsm.feed.adapter.`in`.notice.dto.response

import hs.kr.entrydsm.feed.model.notice.type.NoticeType
import java.time.LocalDateTime
import java.util.*

/**
 * 공지사항 목록 조회 응답을 위한 데이터 클래스입니다.
 *
 * @property id 공지사항 고유 식별자
 * @property title 공지사항 제목
 * @property type 공지사항 유형
 * @property isPinned 공지사항 고정 여부
 * @property createdAt 공지사항 생성 일시
 */
data class NoticeResponse(
    val id: UUID,
    val title: String,
    val type: NoticeType,
    val isPinned: Boolean,
    val createdAt: LocalDateTime,
)
