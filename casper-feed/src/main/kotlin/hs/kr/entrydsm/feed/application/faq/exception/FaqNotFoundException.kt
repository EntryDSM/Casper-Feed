package hs.kr.entrydsm.feed.application.faq.exception

import hs.kr.entrydsm.feed.global.error.exception.CasperException
import hs.kr.entrydsm.feed.global.error.exception.ErrorCode

/**
 * FAQ을 찾을 수 없을 때 발생하는 예외 클래스입니다.
 *
 * @property status HTTP 상태 코드 (404)
 * @property message 에러 메시지
 */
object FaqNotFoundException : CasperException(
    ErrorCode.FAQ_NOT_FOUND,
)
