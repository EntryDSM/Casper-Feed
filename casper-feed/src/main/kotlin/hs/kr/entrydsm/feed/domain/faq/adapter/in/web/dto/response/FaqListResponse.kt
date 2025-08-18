package hs.kr.entrydsm.feed.domain.faq.adapter.`in`.web.dto.response

/**
 * FAQ 목록 응답을 위한 데이터 클래스입니다.
 * 여러 개의 FAQ 정보를 리스트 형태로 반환할 때 사용됩니다.
 *
 * @property faqs FAQ 정보 목록 (FaqDto 리스트)
 */
data class FaqListResponse(
    val faqs: List<FaqDto>,
)
