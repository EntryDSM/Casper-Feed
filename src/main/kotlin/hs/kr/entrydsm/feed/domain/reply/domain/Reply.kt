package hs.kr.entrydsm.feed.domain.reply.domain

import hs.kr.entrydsm.feed.domain.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import java.util.*

@Entity(name = "tbl_reply")
class Reply(
    id: UUID? = null,

    @Column(name = "title", length = 150, nullable = false)
    var title: String,

    @Column(name = "content", length = 5000, nullable = false)
    var content: String,

    @Column(name = "question_id", columnDefinition = "BINARY(16)", nullable = false)
    val questionId: UUID,

    @Column(name = "admin_id", columnDefinition = "BINARY(16)", nullable = false)
    val adminId: UUID
) : BaseEntity(id) {
    fun updateReply(title: String, content: String) {
        this.title = title
        this.content = content
    }
}
