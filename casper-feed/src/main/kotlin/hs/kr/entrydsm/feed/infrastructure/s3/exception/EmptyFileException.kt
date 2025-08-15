package hs.kr.entrydsm.feed.infrastructure.s3.exception

import hs.kr.entrydsm.feed.global.error.exception.CasperException
import hs.kr.entrydsm.feed.global.error.exception.ErrorCode

/**
 * 빈 파일이 업로드되었을 때 발생하는 예외 클래스입니다.
 *
 * @property status HTTP 상태 코드 (400)
 * @property message 에러 메시지
 */
object EmptyFileException : CasperException(
    ErrorCode.FILE_IS_EMPTY,
)
