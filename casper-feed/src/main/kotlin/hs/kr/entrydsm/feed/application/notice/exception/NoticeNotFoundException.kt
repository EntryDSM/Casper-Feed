package hs.kr.entrydsm.feed.application.notice.exception

import hs.kr.entrydsm.feed.global.error.exception.CasperException
import hs.kr.entrydsm.feed.global.error.exception.ErrorCode

/**
 * 공지사항을 찾을 수 없을 때 발생하는 예외 클래스입니다.
 *
 * @property status HTTP 상태 코드 (404)
 * @property message 에러 메시지
 */
object NoticeNotFoundException : CasperException(
    ErrorCode.NOTICE_NOT_FOUND,
)
