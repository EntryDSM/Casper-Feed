package hs.kr.entrydsm.feed.domain.faq.presentation.dto.request

import hs.kr.entrydsm.feed.domain.faq.domain.type.FaqType
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class UpdateFaqRequest(
    @field:NotBlank(message = "title은 null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @field:Size(max = 100, message = "title은 최대 100자까지 가능합니다.")
    val title: String,

    @field:NotBlank(message = "content은 null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @field:Size(max = 5000, message = "content는 최대 5000자까지 가능합니다.")
    val content: String,

    val faqType: FaqType
)
