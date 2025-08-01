package hs.kr.entrydsm.feed.application.reserve.port.`in`

/**
 * 예약 관련 비즈니스 로직을 정의한 인터페이스입니다.
 * 예약 링크 조회 기능을 제공합니다.
 */
interface GetReserveUseCase {
    /**
     * 예약 페이지 링크를 조회합니다.
     *
     * @return 예약 페이지 URL 문자열
     */
    fun execute(): String
}
