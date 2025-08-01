package hs.kr.entrydsm.feed.infrastructure.kafka.configuration

import org.springframework.boot.context.properties.ConfigurationProperties

/**
 * Kafka 관련 설정 프로퍼티를 담는 클래스입니다.
 * application.yml에서 'kafka' 접두사로 시작하는 설정을 매핑합니다.
 *
 * @property serverAddress Kafka 브로커 서버 주소
 * @property confluentApiKey Confluent Cloud API 키
 * @property confluentApiSecret Confluent Cloud API 시크릿
 */
@ConfigurationProperties("kafka")
class KafkaProperty(
    val serverAddress: String,
    val confluentApiKey: String,
    val confluentApiSecret: String,
)
