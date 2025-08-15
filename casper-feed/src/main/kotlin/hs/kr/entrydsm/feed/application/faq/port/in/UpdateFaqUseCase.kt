package hs.kr.entrydsm.feed.application.faq.port.`in`

import hs.kr.entrydsm.feed.adapter.`in`.faq.dto.request.UpdateFaqRequest
import java.util.UUID

/**
 * FAQ 수정을 위한 유스케이스 인터페이스입니다.
 * FAQ 도메인에서 기존 FAQ를 수정하는 역할을 담당합니다.
 */
interface UpdateFaqUseCase {
    /**
     * 특정 FAQ를 수정합니다.
     *
     * @param faqId 수정할 FAQ의 고유 식별자
     * @param updateFaqRequest FAQ 수정 요청 데이터
     */
    fun execute(
        faqId: UUID,
        updateFaqRequest: UpdateFaqRequest,
    )
}
