package hs.kr.entrydsm.feed.application.faq.port.`in`

import java.util.UUID

/**
 * FAQ 삭제를 위한 유스케이스 인터페이스입니다.
 * FAQ 도메인에서 특정 FAQ를 삭제하는 역할을 담당합니다.
 */
interface DeleteFaqUseCase {
    /**
     * 특정 FAQ를 삭제합니다.
     *
     * @param faqId 삭제할 FAQ의 고유 식별자
     */
    fun execute(faqId: UUID)
}
