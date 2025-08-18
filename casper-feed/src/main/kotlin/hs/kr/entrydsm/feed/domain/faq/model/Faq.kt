package hs.kr.entrydsm.feed.domain.faq.model

import hs.kr.entrydsm.feed.domain.faq.model.type.FaqType
import java.time.LocalDateTime
import java.util.UUID

/**
 * 자주 묻는 질문(FAQ) 도메인 모델 클래스입니다.
 *
 * @property id FAQ의 고유 식별자 (생성 시 자동 할당)
 * @property title FAQ 제목
 * @property content FAQ 내용
 * @property faqType FAQ 유형 (enum)
 * @property adminId FAQ를 작성한 관리자 ID
 * @property createdAt FAQ 생성 일시 (기본값: 현재 시간)
 */
data class Faq(
    val id: UUID? = null,
    val title: String,
    val content: String,
    val faqType: FaqType,
    val adminId: UUID,
    val createdAt: LocalDateTime = LocalDateTime.now(),
) {
    /**
     * FAQ 정보를 업데이트합니다.
     *
     * @param newTitle 새로운 제목
     * @param newContent 새로운 내용
     * @param newFaqType 새로운 FAQ 유형
     * @param newAdminId 수정한 관리자 ID
     * @return 업데이트된 FAQ 객체
     */
    fun updateFaq(
        newTitle: String,
        newContent: String,
        newFaqType: FaqType,
        newAdminId: UUID,
    ): Faq {
        return copy(
            title = newTitle,
            content = newContent,
            faqType = newFaqType,
            adminId = newAdminId,
        )
    }
}
