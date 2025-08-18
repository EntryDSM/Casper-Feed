package hs.kr.entrydsm.feed.domain.faq.adapter.`in`.web.dto.response

import hs.kr.entrydsm.feed.domain.faq.model.type.FaqType
import java.util.UUID

/**
 * FAQ 제목과 유형 정보를 포함하는 응답 데이터 클래스입니다.
 * FAQ 목록에서 제목과 유형을 함께 보여줄 때 사용됩니다.
 *
 * @property id FAQ 고유 식별자
 * @property type FAQ 유형 (카테고리)
 * @property title FAQ 제목
 */
data class FaqTitleAndTypeResponse(
    val id: UUID,
    val type: FaqType,
    val title: String,
)
