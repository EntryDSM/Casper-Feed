package hs.kr.entrydsm.feed.global.security.jwt

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.context.SecurityContextHolder.clearContext
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.filter.OncePerRequestFilter

/**
 * JWT(JSON Web Token) 기반 인증을 처리하는 필터 클래스입니다.
 * HTTP 요청 헤더에서 사용자 ID와 역할을 추출하여 Spring Security의 SecurityContext에 인증 정보를 설정합니다.
 *
 * 이 필터는 다음 헤더를 기반으로 인증을 수행합니다:
 * - Request-User-Id: 사용자 고유 식별자
 * - Request-User-Role: 사용자 역할 (UserRole enum 값)
 *
 * @see OncePerRequestFilter
 */
class JwtFilter : OncePerRequestFilter() {
    /**
     * HTTP 요청에서 사용자 인증 정보를 추출하고 SecurityContext에 설정합니다.
     * 인증 정보가 없는 경우 필터 체인을 계속 진행합니다.
     *
     * @param request HTTP 요청 객체
     * @param response HTTP 응답 객체
     * @param filterChain 필터 체인
     */
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val userId: String? = request.getHeader("Request-User-Id")
        val role: UserRole? = request.getHeader("Request-User-Role")?.let { UserRole.valueOf(it) }

        if ((userId == null) || (role == null)) {
            filterChain.doFilter(request, response)
            return
        }

        val authorities = mutableListOf(SimpleGrantedAuthority("ROLE_${role.name}"))
        val userDetails: UserDetails = User(userId, "", authorities)
        val authentication: Authentication =
            UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)

        clearContext()
        SecurityContextHolder.getContext().authentication = authentication
        filterChain.doFilter(request, response)
    }
}

/**
 * 사용자 역할을 정의한 enum 클래스입니다.
 * 애플리케이션에서 사용되는 사용자 유형을 나타냅니다.
 *
 * @property ROOT 최고 관리자 권한
 * @property ADMIN 일반 관리자 권한
 * @property USER 일반 사용자 권한
 */
enum class UserRole {
    ROOT,
    ADMIN,
    USER
}
