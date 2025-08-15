package hs.kr.entrydsm.feed.global.security.jwt

import org.springframework.boot.context.properties.ConfigurationProperties

/**
 * JWT(JSON Web Token) 관련 설정 값을 관리하는 프로퍼티 클래스입니다.
 *
 * 이 클래스는 `application.yml` 또는 `application.yml` 파일에서
 * `auth.jwt` 하위의 설정 값을 주입받아 사용합니다.
 *
 * @property secretKey JWT 서명에 사용되는 비밀 키
 * @property header HTTP 요청 헤더에서 JWT 토큰을 식별하기 위한 헤더 이름
 * @prefix JWT 토큰의 접두사 (예: "Bearer ")
 */
@ConfigurationProperties("jwt")
class JwtProperties(
    val secretKey: String,
    val header: String,
    val prefix: String,
)
