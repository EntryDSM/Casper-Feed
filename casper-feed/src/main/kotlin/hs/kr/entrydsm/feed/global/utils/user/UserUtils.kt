package hs.kr.entrydsm.feed.global.utils.user

import hs.kr.entrydsm.feed.global.exception.InvalidTokenException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import java.util.UUID

/**
 * 현재 인증된 사용자와 관련된 유틸리티 메서드를 제공하는 컴포넌트입니다.
 * Spring Security의 SecurityContext를 사용하여 현재 인증된 사용자 정보를 조회합니다.
 */
@Component
class UserUtils {

    /**
     * 현재 인증된 사용자의 고유 식별자(UUID)를 조회합니다.
     *
     * @return 현재 인증된 사용자의 UUID
     * @throws hs.kr.entrydsm.feed.global.exception.InvalidTokenException 인증 토큰이 유효하지 않은 경우
     */
    fun getCurrentUserId(): UUID {
        try {
            return UUID.fromString(SecurityContextHolder.getContext().authentication.name)
        } catch (e: IllegalArgumentException) {
            throw InvalidTokenException
        }
    }
}
