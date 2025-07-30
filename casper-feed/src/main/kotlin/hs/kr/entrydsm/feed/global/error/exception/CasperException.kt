package hs.kr.entrydsm.feed.global.error.exception

import java.lang.RuntimeException

/**
 * 애플리케이션에서 발생하는 예외들의 기본 추상 클래스입니다.
 * 모든 커스텀 예외는 이 클래스를 상속받아 구현해야 합니다.
 *
 * @property errorCode 예외에 해당하는 에러 코드
 */
abstract class CasperException(
    val errorCode: ErrorCode,
) : RuntimeException()
