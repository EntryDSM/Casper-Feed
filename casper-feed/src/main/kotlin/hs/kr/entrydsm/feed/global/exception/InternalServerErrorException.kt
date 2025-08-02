package hs.kr.entrydsm.feed.global.exception

import hs.kr.entrydsm.feed.global.error.exception.CasperException
import hs.kr.entrydsm.feed.global.error.exception.ErrorCode

/**
 * 서버 내부에서 예기치 않은 오류가 발생했을 때 발생하는 예외 클래스입니다.
 *
 * @property status HTTP 상태 코드 (500)
 * @property message 에러 메시지
 */
object InternalServerErrorException : CasperException(
    ErrorCode.INTERNAL_SERVER_ERROR,
)
