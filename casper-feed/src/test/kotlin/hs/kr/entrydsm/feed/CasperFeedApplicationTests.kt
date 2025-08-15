package hs.kr.entrydsm.feed

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

/**
 * Casper Feed 애플리케이션의 통합 테스트 클래스입니다.
 *
 * 이 클래스는 Spring 애플리케이션 컨텍스트가 모든 필요한 빈과 설정과 함께
 * 성공적으로 로드되는지 검증합니다.
 */
@SpringBootTest(classes = [CasperFeedApplication::class])
class CasperFeedApplicationTests {

    /**
     * 애플리케이션 컨텍스트가 성공적으로 로드되는지 테스트합니다.
     *
     * 이 테스트는 Spring 애플리케이션 컨텍스트가 모든 필요한 설정과 빈이 제대로
     * 초기화된 상태로 시작될 수 있는지 확인합니다.
     * 컨텍스트 로딩에 실패할 경우 이 테스트는 실패합니다.
     */
    @Test
    fun contextLoads() {
        // 애플리케이션 컨텍스트가 성공적으로 로드되면 테스트가 통과합니다.
    }
}
