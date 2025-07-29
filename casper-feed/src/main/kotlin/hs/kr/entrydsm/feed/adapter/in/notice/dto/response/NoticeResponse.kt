package hs.kr.entrydsm.feed.adapter.`in`.notice.dto.response

import hs.kr.entrydsm.feed.model.notice.type.NoticeType
import java.time.LocalDateTime
import java.util.*

data class NoticeResponse(
    val id: UUID,
    val title: String,
    val type: NoticeType,
    val isPinned: Boolean,
    val createdAt: LocalDateTime,
)
