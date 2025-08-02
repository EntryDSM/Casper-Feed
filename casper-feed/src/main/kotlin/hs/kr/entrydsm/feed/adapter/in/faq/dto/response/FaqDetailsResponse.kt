package hs.kr.entrydsm.feed.adapter.`in`.faq.dto.response

import hs.kr.entrydsm.feed.model.faq.type.FaqType
import java.time.LocalDateTime

/**
 * FAQ 상세 조회 응답을 위한 데이터 클래스입니다.
 * FAQ의 상세 내용을 보여줄 때 사용됩니다.
 *
 * @property title FAQ 제목
 * @property content FAQ 상세 내용
 * @property createdAt FAQ 생성 일시
 * @property faqType FAQ 유형 (카테고리)
 */
data class FaqDetailsResponse(
    val title: String,
    val content: String,
    val createdAt: LocalDateTime,
    val faqType: FaqType,
)
