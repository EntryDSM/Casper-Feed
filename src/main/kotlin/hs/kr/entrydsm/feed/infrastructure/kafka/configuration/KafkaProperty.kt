package hs.kr.entrydsm.feed.infrastructure.kafka.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("kafka")
class KafkaProperty(
    val serverAddress: String,
    val confluentApiKey: String,
    val confluentApiSecret: String
)
