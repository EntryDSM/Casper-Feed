package hs.kr.entrydsm.feed.application.faq.service

import hs.kr.entrydsm.feed.adapter.`in`.faq.dto.request.CreateFaqRequest
import hs.kr.entrydsm.feed.application.faq.port.`in`.CreateFaqUseCase
import hs.kr.entrydsm.feed.application.faq.port.out.SaveFaqPort
import hs.kr.entrydsm.feed.global.utils.user.UserUtils
import hs.kr.entrydsm.feed.model.faq.Faq
import org.springframework.stereotype.Service

/**
 * FAQ 생성을 처리하는 서비스 클래스입니다.
 *
 * @property saveFaqPort FAQ 저장을 위한 포트
 * @property userUtils 현재 사용자 정보를 조회하기 위한 유틸리티
 */
@Service
class CreateFaqService(
    private val saveFaqPort: SaveFaqPort,
    private val userUtils: UserUtils,
) : CreateFaqUseCase {
    /**
     * 새로운 FAQ를 생성합니다.
     *
     * @param createFaqRequest FAQ 생성 요청 데이터
     * @throws hs.kr.entrydsm.feed.global.exception.UnauthorizedException 현재 사용자가 인증되지 않은 경우
     */
    override fun execute(createFaqRequest: CreateFaqRequest) {
        val faq =
            Faq(
                title = createFaqRequest.title,
                content = createFaqRequest.content,
                faqType = createFaqRequest.faqType,
                adminId = userUtils.getCurrentUserId(),
            )

        saveFaqPort.saveFaq(faq)
    }
}
