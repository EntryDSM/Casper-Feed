package hs.kr.entrydsm.feed.domain.reserve.application.service

import hs.kr.entrydsm.feed.domain.reserve.application.port.`in`.GetReserveUseCase
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

/**
 * 예약 링크를 제공하는 서비스 클래스입니다.
 *
 * 이 클래스는 애플리케이션 설정에서 예약 링크를 가져와 제공하는 역할을 합니다.
 *
 * @property reserveLink 애플리케이션 설정에서 주입받은 예약 링크 (reserve.link)
 */
@Service
class GetReserveService(
    @Value("\${reserve.link}")
    private val reserveLink: String,
) : GetReserveUseCase {
    /**
     * 애플리케이션 설정에 정의된 예약 링크를 반환합니다.
     *
     * @return 예약 페이지 URL 문자열
     */
    override fun execute() = reserveLink
}
