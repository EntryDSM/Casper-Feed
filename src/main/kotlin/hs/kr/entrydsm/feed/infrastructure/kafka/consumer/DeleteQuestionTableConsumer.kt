package hs.kr.entrydsm.feed.infrastructure.kafka.consumer

import hs.kr.entrydsm.feed.domain.question.domain.repository.QuestionRepository
import hs.kr.entrydsm.feed.infrastructure.kafka.configuration.KafkaTopics
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DeleteQuestionTableConsumer(
    private val questionRepository: QuestionRepository
) {
    @KafkaListener(
        topics = [KafkaTopics.DELETE_ALL_TABLE],
        groupId = "delete-all-table-question",
        containerFactory = "kafkaListenerContainerFactory"
    )
    @Transactional
    fun execute() = questionRepository.deleteAll()
}
