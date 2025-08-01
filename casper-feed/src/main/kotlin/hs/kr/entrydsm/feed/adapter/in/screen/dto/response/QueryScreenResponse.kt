package hs.kr.entrydsm.feed.adapter.`in`.screen.dto.response

import java.time.LocalDateTime
import java.util.UUID

/**
 * 화면 정보 조회 응답을 위한 데이터 클래스입니다.
 * 화면의 상세 정보와 메타데이터를 클라이언트에 반환할 때 사용됩니다.
 *
 * @property id 화면 정보의 고유 식별자 (UUID)
 * @property image 화면에 표시될 이미지의 URL 또는 경로
 * @property createAt 화면 정보가 생성된 일시
 * @property modifyAt 화면 정보가 마지막으로 수정된 일시
 */
data class QueryScreenResponse(
    val id: UUID,
    val image: String,
    val createAt: LocalDateTime,
    val modifyAt: LocalDateTime,
)
