package hs.kr.entrydsm.feed.application.faq.port.`in`

import hs.kr.entrydsm.feed.adapter.`in`.faq.dto.request.CreateFaqRequest

/**
 * FAQ 생성을 위한 유스케이스 인터페이스입니다.
 * FAQ 도메인에서 새로운 FAQ를 생성하는 역할을 담당합니다.
 */
interface CreateFaqUseCase {
    /**
     * 새로운 FAQ를 생성합니다.
     *
     * @param createFaqRequest FAQ 생성 요청 데이터
     */
    fun execute(createFaqRequest: CreateFaqRequest)
}
