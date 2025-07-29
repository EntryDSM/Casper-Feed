package hs.kr.entrydsm.feed.adapter.`in`.notice.dto.response

import hs.kr.entrydsm.feed.model.notice.type.NoticeType
import java.time.LocalDateTime

data class QueryDetailsNoticeResponse(
    val title: String,
    val content: String,
    val createdAt: LocalDateTime,
    val type: NoticeType,
    val imageURL: String?,
    val imageName: String?,
    val attachFiles: List<AttachFileElement> = emptyList(),
    val isPinned: Boolean,
)

data class AttachFileElement(
    val attachFileUrl: String,
    val attachFileName: String,
)
