package hs.kr.entrydsm.feed.adapter.`in`.faq.dto.response

import java.util.UUID

/**
 * FAQ 제목 목록 조회 응답을 위한 데이터 클래스입니다.
 * FAQ 목록에서 제목과 간략한 내용을 보여줄 때 사용됩니다.
 *
 * @property id FAQ 고유 식별자
 * @property title FAQ 제목
 * @property content FAQ 내용 (요약된 내용일 수 있음)
 */
data class FaqTitleResponse(
    val id: UUID,
    val title: String,
    val content: String,
)
