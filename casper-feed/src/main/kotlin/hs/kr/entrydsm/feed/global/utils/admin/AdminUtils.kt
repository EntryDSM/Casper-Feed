package hs.kr.entrydsm.feed.global.utils.admin

import hs.kr.entrydsm.feed.global.exception.InvalidTokenException
import hs.kr.entrydsm.feed.infrastructure.grpc.user.client.UserGrpcClient
import hs.kr.entrydsm.feed.infrastructure.grpc.user.client.dto.response.InternalAdminResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import java.util.*

/**
 * 현재 인증된 관리자와 관련된 유틸리티 메서드를 제공하는 컴포넌트입니다.
 * Spring Security의 SecurityContext를 사용하여 현재 인증된 관리자 정보를 조회하고,
 * gRPC를 통해 관리자 서비스에서 추가 정보를 조회합니다.
 *
 * @property userGrpcClient 관리자 정보 조회를 위한 gRPC 클라이언트
 */
@Component
class AdminUtils(
    private val userGrpcClient: UserGrpcClient
) {

    /**
     * 현재 인증된 관리자의 상세 정보를 조회합니다.
     * 내부적으로 [getCurrentAdminId]를 호출하여 관리자 ID를 가져온 후,
     * gRPC를 통해 관리자 서비스에서 상세 정보를 조회합니다.
     *
     * @return 현재 인증된 관리자의 상세 정보 [InternalAdminResponse]
     * @throws hs.kr.entrydsm.feed.global.exception.InvalidTokenException 인증 토큰이 유효하지 않은 경우
     * @throws io.grpc.StatusRuntimeException gRPC 통신 중 오류가 발생한 경우
     */
    suspend fun getCurrentAdmin(): InternalAdminResponse = userGrpcClient.getAdminInfoByAdminId(getCurrentAdminId())

    /**
     * 현재 인증된 관리자의 고유 식별자(UUID)를 조회합니다.
     * Spring Security의 SecurityContext에서 인증 정보를 추출하여 반환합니다.
     *
     * @return 현재 인증된 관리자의 UUID
     * @throws hs.kr.entrydsm.feed.global.exception.InvalidTokenException 인증 토큰이 유효하지 않은 경우
     */
    fun getCurrentAdminId(): UUID {
        try {
            return UUID.fromString(SecurityContextHolder.getContext().authentication.name)
        } catch (e: IllegalArgumentException) {
            throw InvalidTokenException
        }
    }
}
