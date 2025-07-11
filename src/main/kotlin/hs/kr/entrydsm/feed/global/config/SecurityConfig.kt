package hs.kr.entrydsm.feed.global.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig(
    private val objectMapper: ObjectMapper
) {
    companion object {
        private const val ADMIN_ROLE = "ADMIN"
    }

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
                    .requestMatchers("/reply/**")
                    .hasRole(ADMIN_ROLE)
                    .requestMatchers(HttpMethod.POST, "/faq/**")
                    .hasRole(ADMIN_ROLE)
                    .requestMatchers(HttpMethod.PATCH, "/faq/**")
                    .hasRole(ADMIN_ROLE)
                    .requestMatchers(HttpMethod.DELETE, "/faq/**")
                    .hasRole(ADMIN_ROLE)
                    .requestMatchers(HttpMethod.GET, "/faq/all/title-type")
                    .hasRole(ADMIN_ROLE)
                    .requestMatchers(HttpMethod.GET, "/faq/**")
                    .permitAll()
                    .requestMatchers(HttpMethod.GET, "/reserve/**")
                    .permitAll()
                    .requestMatchers(HttpMethod.POST, "/notice")
                    .hasRole(ADMIN_ROLE)
                    .requestMatchers(HttpMethod.PATCH, "/notice/**")
                    .hasRole(ADMIN_ROLE)
                    .requestMatchers(HttpMethod.POST, "/notice/image")
                    .hasRole(ADMIN_ROLE)
                    .requestMatchers(HttpMethod.POST, "/screen")
                    .hasRole(ADMIN_ROLE)
                    .requestMatchers(HttpMethod.POST, "/attach-file")
                    .hasRole(ADMIN_ROLE)
                    .requestMatchers(HttpMethod.GET, "/notice/**")
                    .permitAll()
                    .anyRequest()
                    .authenticated()
            }
            .with(FilterConfig(objectMapper)) { }
            .build()

        return http.build()
    }
}
