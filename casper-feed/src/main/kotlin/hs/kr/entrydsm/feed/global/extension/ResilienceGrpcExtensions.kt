package hs.kr.entrydsm.feed.global.extension

import io.github.resilience4j.circuitbreaker.CircuitBreaker
import io.github.resilience4j.kotlin.circuitbreaker.executeSuspendFunction
import io.github.resilience4j.kotlin.retry.executeSuspendFunction
import io.github.resilience4j.retry.Retry
import kotlinx.coroutines.CancellationException
import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("ResilienceGrpcExtensions")

/**
 * gRPC 호출에 대해 Retry와 CircuitBreaker를 적용합니다.
 *
 * @param T gRPC 함수의 반환 타입
 * @param retry 적용할 Retry 인스턴스
 * @param circuitBreaker 적용할 CircuitBreaker 인스턴스
 * @param fallback gRPC 호출 실패 시 실행할 함수
 * @param block 실행할 gRPC 함수
 * @return gRPC 함수의 실행 결과를 반환하거나, 실패 시 fallback 함수의 결과를 반환
 */
suspend fun <T> executeGrpcCallWithResilience(
    retry: Retry,
    circuitBreaker: CircuitBreaker,
    fallback: suspend () -> T,
    block: suspend () -> T
): T =
    try {
        retry.executeSuspendFunction {
            circuitBreaker.executeSuspendFunction(block)
        }
    } catch (ce: CancellationException) {
        throw ce
    } catch (e: Exception) {
        log.warn("gRPC 호출 실패, fallback 실행: {}", e.toString())
        fallback()
    }
