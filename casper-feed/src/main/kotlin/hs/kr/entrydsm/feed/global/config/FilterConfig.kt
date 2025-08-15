package hs.kr.entrydsm.feed.global.config

import com.fasterxml.jackson.databind.ObjectMapper
import hs.kr.entrydsm.feed.global.error.GlobalExceptionFilter
import hs.kr.entrydsm.feed.global.security.jwt.JwtFilter
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.stereotype.Component

/**
 * Spring Security 필터 체인을 구성하는 설정 클래스입니다.
 * JWT 인증 필터와 글로벌 예외 처리 필터를 적절한 위치에 추가합니다.
 *
 * @property objectMapper JSON 직렬화/역직렬화를 위한 ObjectMapper
 *
 * @see SecurityConfigurerAdapter
 * @see JwtFilter
 * @see GlobalExceptionFilter
 */
@Component
class FilterConfig(
    private val objectMapper: ObjectMapper
) : SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {
    /**
     * Spring Security 필터 체인에 커스텀 필터들을 추가합니다.
     * 1. JwtFilter: JWT 기반 인증 처리
     * 2. GlobalExceptionFilter: 전역 예외 처리
     *
     * @param builder HttpSecurity 빌더 객체
     */
    override fun configure(builder: HttpSecurity) {
        builder.addFilterBefore(
            JwtFilter(),
            UsernamePasswordAuthenticationFilter::class.java
        )
        builder.addFilterBefore(GlobalExceptionFilter(objectMapper), JwtFilter::class.java)
    }
}
