package hs.kr.entrydsm.feed.infrastructure.kafka.consumer

import hs.kr.entrydsm.feed.adapter.out.persistence.faq.repository.FaqRepository
import hs.kr.entrydsm.feed.infrastructure.kafka.configuration.KafkaTopics
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * FAQ 테이블 삭제를 처리하는 Kafka Consumer 클래스입니다.
 *
 * @property faqRepository FAQ 데이터 접근을 위한 리포지토리
 */
@Service
class DeleteFaqTableConsumer(
    private val faqRepository: FaqRepository,
) {
    /**
     * Kafka 토픽에서 메시지를 수신하여 FAQ 테이블의 모든 데이터를 삭제합니다.
     * DELETE_ALL_TABLE 토픽으로 메시지가 수신되면 실행됩니다.
     *
     * @see KafkaTopics.DELETE_ALL_TABLE
     */
    @KafkaListener(
        topics = [KafkaTopics.DELETE_ALL_TABLE],
        groupId = "delete-all-table-faq",
        containerFactory = "kafkaListenerContainerFactory",
    )
    @Transactional
    fun execute() {
        faqRepository.deleteAll()
    }
}
