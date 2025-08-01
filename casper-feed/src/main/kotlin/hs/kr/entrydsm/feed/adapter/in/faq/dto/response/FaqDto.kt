package hs.kr.entrydsm.feed.adapter.`in`.faq.dto.response

import hs.kr.entrydsm.feed.model.faq.type.FaqType
import java.time.LocalDateTime
import java.util.UUID

/**
 * FAQ 정보를 나타내는 데이터 전송 객체(DTO)입니다.
 * FAQ 목록 조회 시 각 FAQ 항목의 정보를 담는 데 사용됩니다.
 *
 * @property id FAQ 고유 식별자
 * @property title FAQ 제목
 * @property content FAQ 내용
 * @property createdAt FAQ 생성 일시
 * @property faqType FAQ 유형 (카테고리)
 */
data class FaqDto(
    val id: UUID,
    val title: String,
    val content: String,
    val createdAt: LocalDateTime,
    val faqType: FaqType,
)
