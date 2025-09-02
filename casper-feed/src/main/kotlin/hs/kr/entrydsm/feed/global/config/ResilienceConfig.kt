package hs.kr.entrydsm.feed.global.config

import io.github.resilience4j.circuitbreaker.CircuitBreaker
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry
import io.github.resilience4j.retry.Retry
import io.github.resilience4j.retry.RetryRegistry
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Resilience4j 관련 설정을 담당하는 클래스입니다.
 *
 * @property circuitBreakerRegistry CircuitBreaker 인스턴스를 생성하고 관리합니다.
 * @property retryRegistry Retry 인스턴스를 생성하고 관리합니다.
 */
@Configuration
class ResilienceConfig(
    private val circuitBreakerRegistry: CircuitBreakerRegistry,
    private val retryRegistry: RetryRegistry
) {

    /**
     * user-grpc 서킷 브레이커를 생성합니다.
     *
     * @return user-grpc 이름의 CircuitBreaker 인스턴스
     */
    @Bean
    fun userGrpcCircuitBreaker(): CircuitBreaker {
        return circuitBreakerRegistry.circuitBreaker("user-grpc")
    }

    /**
     * user-grpc 리트라이를 생성합니다.
     *
     * @return user-grpc 이름의 Retry 인스턴스
     */
    @Bean
    fun userGrpcRetry(): Retry {
        return retryRegistry.retry("user-grpc")
    }
}
