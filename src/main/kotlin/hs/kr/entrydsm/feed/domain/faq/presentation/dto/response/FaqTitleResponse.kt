package hs.kr.entrydsm.feed.domain.faq.presentation.dto.response

import java.util.UUID

data class FaqTitleResponse(
    val id: UUID,
    val title: String,
    val content: String
)
