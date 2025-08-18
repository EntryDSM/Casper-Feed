package hs.kr.entrydsm.feed.domain.reserve.adapter.`in`.web

import hs.kr.entrydsm.feed.domain.reserve.application.port.`in`.GetReserveUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * 예약 링크 관련 HTTP 요청을 처리하는 웹 어댑터 클래스입니다.
 *
 * 이 클래스는 예약 링크 조회와 관련된 HTTP 엔드포인트를 제공하며,
 * 클라이언트의 요청을 적절한 서비스 메서드로 라우팅합니다.
 *
 * @property getReserveUseCase 예약 링크 비즈니스 로직을 처리하는 서비스
 */
@RestController
@RequestMapping("/reserve")
class ReserveWebAdapter(
    private val getReserveUseCase: GetReserveUseCase,
) {
    /**
     * 예약 페이지 링크를 조회합니다.
     *
     * @return 예약 페이지 URL 문자열
     */
    @GetMapping
    fun reserveLink(): String = getReserveUseCase.execute()
}
