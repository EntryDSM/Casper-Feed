package hs.kr.entrydsm.feed.domain.notice.adapter.`in`.web.dto.response

import java.time.LocalDateTime
import java.util.UUID

/**
 * 공지사항 제목 목록 조회 응답을 위한 데이터 클래스입니다.
 * 주로 공지사항 목록에서 제목만 보여줄 때 사용됩니다.
 *
 * @property id 공지사항 고유 식별자
 * @property title 공지사항 제목
 * @property createdAt 공지사항 생성 일시
 */
data class QueryNoticeTitleResponse(
    val id: UUID,
    val title: String,
    val createdAt: LocalDateTime,
)
