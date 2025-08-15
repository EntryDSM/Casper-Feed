package hs.kr.entrydsm.feed.application.screen.port.out

import hs.kr.entrydsm.feed.model.screen.Screen
import java.util.UUID

/**
 * 화면 조회를 위한 포트 인터페이스입니다.
 * 화면 도메인 객체를 다양한 방식으로 조회하는 메서드를 정의합니다.
 */
interface FindScreenPort {
    /**
     * 모든 화면을 조회합니다.
     *
     * @return 모든 화면 목록 (없을 경우 빈 목록)
     */
    fun findAll(): List<Screen>

    /**
     * 화면 ID로 화면을 조회합니다.
     *
     * @param screenId 조회할 화면의 고유 식별자
     * @return 조회된 화면 객체, 없을 경우 null
     */
    fun findByIdOrNull(screenId: UUID): Screen?
}
