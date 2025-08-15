package hs.kr.entrydsm.feed.infrastructure.kafka.configuration

/**
 * Kafka 토픽 이름을 상수로 정의한 객체입니다.
 * 애플리케이션 전체에서 일관된 토픽 이름을 사용하기 위해 상수로 관리합니다.
 */
object KafkaTopics {
    /**
     * 모든 테이블 데이터 삭제를 위한 토픽 이름
     */
    const val DELETE_ALL_TABLE = "delete-all-table"

    /**
     * 사용자 삭제 이벤트를 위한 토픽 이름
     */
    const val DELETE_USER = "delete-user"
}
