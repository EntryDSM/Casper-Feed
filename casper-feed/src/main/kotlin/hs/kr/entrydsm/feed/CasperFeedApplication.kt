package hs.kr.entrydsm.feed

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

/**
 * CasperFeed 애플리케이션의 메인 클래스입니다.
 *
 * 이 클래스는 스프링 부트 애플리케이션을 시작하고 자동 구성을 활성화합니다.
 * `@ConfigurationPropertiesScan` 어노테이션을 통해 설정 프로퍼티 클래스들을 스캔합니다.
 */
@SpringBootApplication
@ConfigurationPropertiesScan
class CasperFeedApplication

/**
 * 애플리케이션의 진입점입니다.
 *
 * @param args 명령행 인자 배열
 */
fun main(args: Array<String>) {
    runApplication<CasperFeedApplication>(*args)
}
