package hs.kr.entrydsm.feed.domain.faq.application.port.out

import hs.kr.entrydsm.feed.domain.faq.model.Faq

/**
 * FAQ 저장을 위한 포트 인터페이스입니다.
 * FAQ 도메인 객체를 저장하는 메서드를 정의합니다.
 */
interface SaveFaqPort {
    /**
     * FAQ를 저장하거나 업데이트합니다.
     *
     * @param faq 저장할 FAQ 도메인 객체
     * @return 저장된 FAQ 도메인 객체 (ID가 할당됨)
     */
    fun saveFaq(faq: Faq): Faq
}
