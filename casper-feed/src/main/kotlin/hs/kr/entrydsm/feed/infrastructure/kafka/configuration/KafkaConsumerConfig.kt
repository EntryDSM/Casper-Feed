package hs.kr.entrydsm.feed.infrastructure.kafka.configuration

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.JsonDeserializer

/**
 * Kafka Consumer 설정을 담당하는 클래스입니다.
 * Kafka Consumer의 기본 설정과 보안 설정을 구성합니다.
 *
 * @property kafkaProperty Kafka 관련 설정 프로퍼티
 */
@EnableKafka
@Configuration
class KafkaConsumerConfig(
    private val kafkaProperty: KafkaProperty,
) {
    /**
     * Kafka ConsumerFactory를 생성하는 빈을 정의합니다.
     *
     * @return ConsumerFactory<String, Any> Kafka Consumer 인스턴스를 생성하는 팩토리
     */
    @Bean
    fun consumerFactory(): ConsumerFactory<String, Any> {
        return DefaultKafkaConsumerFactory(consumerFactoryConfig())
    }

    /**
     * Kafka Listener Container Factory를 생성하는 빈을 정의합니다.
     * 동시성 및 컨테이너 속성을 설정합니다.
     *
     * @return ConcurrentKafkaListenerContainerFactory<String, Any> Kafka Listener Container 팩토리
     */
    @Bean
    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, Any> {
        return ConcurrentKafkaListenerContainerFactory<String, Any>().apply {
            consumerFactory = consumerFactory()
            setConcurrency(2)
            // Spring Kafka 3.1.x에서는 setMessageConverter 제거됨
            // JSON 변환은 JsonDeserializer에서 처리
            containerProperties.apply {
                pollTimeout = 500
                isMissingTopicsFatal = false
                isObservationEnabled = true
            }
        }
    }

    /**
     * Kafka ConsumerFactory에 사용할 설정을 생성합니다.
     *
     * @return Map<String, Any> Kafka Consumer 설정 맵
     */
    private fun consumerFactoryConfig(): Map<String, Any> {
        return mapOf(
            // 기본 설정
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to kafkaProperty.serverAddress,
            ConsumerConfig.ISOLATION_LEVEL_CONFIG to "read_committed",
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to JsonDeserializer::class.java,
            ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG to false,
            ConsumerConfig.AUTO_OFFSET_RESET_CONFIG to "latest",
            ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG to 5000,
            ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG to 30000,
            ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG to 10000,
            // JsonDeserializer 설정 (3.1.x 방식)
            JsonDeserializer.TRUSTED_PACKAGES to "*",
            JsonDeserializer.TYPE_MAPPINGS to "",
            JsonDeserializer.USE_TYPE_INFO_HEADERS to false,
            JsonDeserializer.VALUE_DEFAULT_TYPE to "java.lang.Object",
        )
    }

    /**
     * Kafka 인증을 위한 JAAS 구성을 생성합니다.
     *
     * @return String JAAS 구성 문자열
     */
    private fun buildJaasConfig(): String {
        return "org.apache.kafka.common.security.scram.ScramLoginModule required " +
            "username=\"${kafkaProperty.confluentApiKey}\" " +
            "password=\"${kafkaProperty.confluentApiSecret}\";"
    }
}
