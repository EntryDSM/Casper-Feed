package hs.kr.entrydsm.feed.infrastructure.grpc.client.dto.response

import java.util.UUID

/**
 * 관리자 정보를 담는 데이터 클래스입니다.
 *
 * @property id 관리자의 고유 식별자(UUID)
 */
data class InternalAdminResponse(
    val id: UUID
)
