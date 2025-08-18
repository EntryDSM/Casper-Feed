package hs.kr.entrydsm.feed.domain.screen.application.port.out

import hs.kr.entrydsm.feed.domain.screen.model.Screen

/**
 * 화면 저장을 위한 포트 인터페이스입니다.
 * 화면 도메인 객체를 저장하거나 업데이트하는 메서드를 정의합니다.
 */
interface SaveScreenPort {
    /**
     * 화면을 저장하거나 업데이트합니다.
     *
     * @param screen 저장하거나 업데이트할 화면 도메인 객체
     */
    fun saveScreen(screen: Screen)
}
