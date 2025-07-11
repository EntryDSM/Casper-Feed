package hs.kr.entrydsm.feed.domain.screen.domain

import hs.kr.entrydsm.feed.domain.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import java.util.UUID

@Entity(name = "tbl_screen")
class Screen(

    id: UUID? = null,

    var image: String,

    @Column(columnDefinition = "BINARY(16)")
    val adminId: UUID
) : BaseEntity(id) {

    fun updateImage(image: String) {
        this.image = image
    }
}
