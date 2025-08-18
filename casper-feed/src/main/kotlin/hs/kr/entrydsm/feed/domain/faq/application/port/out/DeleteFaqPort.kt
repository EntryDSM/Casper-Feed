package hs.kr.entrydsm.feed.domain.faq.application.port.out

import hs.kr.entrydsm.feed.domain.faq.model.Faq

/**
 * FAQ 삭제를 위한 포트 인터페이스입니다.
 * FAQ 도메인 객체를 삭제하는 메서드를 정의합니다.
 */
interface DeleteFaqPort {
    /**
     * FAQ를 삭제합니다.
     *
     * @param faq 삭제할 FAQ 도메인 객체
     */
    fun deleteFaq(faq: Faq)
}
