package hs.kr.entrydsm.feed.global.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain

/**
 * Spring Security 구성을 위한 설정 클래스입니다.
 * 애플리케이션의 보안 설정(인증, 인가, CORS, CSRF 등)을 담당합니다.
 *
 * @property objectMapper JSON 직렬화/역직렬화를 위한 ObjectMapper
 *
 * @see SecurityFilterChain
 * @see HttpSecurity
 */
@Configuration
class SecurityConfig(
    private val objectMapper: ObjectMapper
) {
    companion object {
        private const val ADMIN_ROLE = "ADMIN"
    }

    /**
     * Spring Security 필터 체인을 구성하는 빈 메서드입니다.
     * CSRF, CORS, 세션 정책, 인증/인가 규칙 등을 설정합니다.
     *
     * @param http HttpSecurity 구성 객체
     * @return 구성된 SecurityFilterChain 인스턴스
     */
    @Bean
    protected fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() }
            .cors { }
            .formLogin { it.disable() }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authorizeHttpRequests { auth ->
                auth
                    .requestMatchers("/")
                    .permitAll()
                    .requestMatchers(HttpMethod.POST, "/notice")
                    .hasRole(ADMIN_ROLE)
                    .requestMatchers(HttpMethod.PATCH, "/notice/**")
                    .hasRole(ADMIN_ROLE)
                    .requestMatchers(HttpMethod.POST, "/notice/image")
                    .hasRole(ADMIN_ROLE)
                    .requestMatchers(HttpMethod.POST, "/attach-file")
                    .hasRole(ADMIN_ROLE)
                    .requestMatchers(HttpMethod.GET, "/notice/**")
                    .permitAll()
                    .requestMatchers("/swagger-ui/**")
                    .permitAll()
                    .requestMatchers("/v3/api-docs/**")
                    .permitAll()
                    .requestMatchers("/swagger-resources/**")
                    .permitAll()
                    .requestMatchers("/webjars/**")
                    .permitAll()
                    .anyRequest()
                    .authenticated()
            }
            .with(FilterConfig(objectMapper)) { }

        return http.build()
    }
}
