package hs.kr.entrydsm.feed.global.exception

import hs.kr.entrydsm.feed.global.error.exception.CasperException
import hs.kr.entrydsm.feed.global.error.exception.ErrorCode

/**
 * 유효하지 않은 JWT 토큰이 전달되었을 때 발생하는 예외 클래스입니다.
 *
 * @property status HTTP 상태 코드 (401)
 * @property message 에러 메시지
 */
object InvalidTokenException : CasperException(
    ErrorCode.INVALID_TOKEN,
)
