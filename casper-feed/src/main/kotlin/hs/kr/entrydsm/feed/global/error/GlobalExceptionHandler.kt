package hs.kr.entrydsm.feed.global.error

import hs.kr.entrydsm.feed.global.error.exception.CasperException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

/**
 * 전역 예외 처리를 담당하는 핸들러 클래스입니다.
 *
 * 이 클래스는 컨트롤러 계층에서 발생하는 예외를 중앙 집중적으로 처리하며,
 * 적절한 HTTP 응답을 생성하여 클라이언트에 반환합니다.
 *
 * - `CasperException`: 비즈니스 로직에서 발생한 예외를 처리합니다.
 * - `MethodArgumentNotValidException`: 유효성 검증 실패 시 발생하는 예외를 처리합니다.
 *
 * @see RestControllerAdvice
 * @see ExceptionHandler
 */
@RestControllerAdvice
class GlobalExceptionHandler() {
    /**
     * EquusException을 처리하는 메서드입니다.
     * 비즈니스 로직에서 발생한 예외를 적절한 HTTP 응답으로 변환합니다.
     *
     * @param e 처리할 CasperException 예외 객체
     * @return ErrorResponse를 포함하는 ResponseEntity
     */
    @ExceptionHandler(CasperException::class)
    fun handlingCasperException(e: CasperException): ResponseEntity<ErrorResponse> {
        val code = e.errorCode
        return ResponseEntity(
            ErrorResponse(code.status, code.message),
            HttpStatus.valueOf(code.status),
        )
    }

    /**
     * MethodArgumentNotValidException을 처리하는 메서드입니다.
     * 유효성 검증 실패 시 발생하는 예외를 처리합니다.
     *
     * @param e 처리할 MethodArgumentNotValidException 예외 객체
     * @return ErrorResponse를 포함하는 ResponseEntity (400 Bad Request)
     */
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun validatorExceptionHandler(e: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(
            ErrorResponse(
                400,
                e.bindingResult.allErrors[0].defaultMessage,
            ),
            HttpStatus.BAD_REQUEST,
        )
    }
}
