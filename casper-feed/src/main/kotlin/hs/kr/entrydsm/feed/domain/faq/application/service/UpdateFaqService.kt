package hs.kr.entrydsm.feed.domain.faq.application.service

import hs.kr.entrydsm.feed.domain.faq.adapter.`in`.web.dto.request.UpdateFaqRequest
import hs.kr.entrydsm.feed.domain.faq.application.port.`in`.UpdateFaqUseCase
import hs.kr.entrydsm.feed.domain.faq.application.port.out.FindFaqPort
import hs.kr.entrydsm.feed.domain.faq.exception.FaqNotFoundException
import hs.kr.entrydsm.feed.global.utils.user.UserUtils
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

/**
 * FAQ 수정을 처리하는 서비스 클래스입니다.
 *
 * @property findFaqPort FAQ 조회를 위한 포트
 * @property userUtils 현재 사용자 정보를 조회하기 위한 유틸리티
 */
@Service
@Transactional
class UpdateFaqService(
    private val findFaqPort: FindFaqPort,
    private val userUtils: UserUtils,
) : UpdateFaqUseCase {
    /**
     * 지정된 ID의 FAQ를 업데이트합니다.
     *
     * @param faqId 업데이트할 FAQ의 고유 식별자
     * @param updateFaqRequest FAQ 업데이트 요청 데이터
     * @throws hs.kr.entrydsm.feed.domain.faq.exception.FaqNotFoundException 지정된 ID의 FAQ를 찾을 수 없는 경우
     * @throws hs.kr.entrydsm.feed.global.exception.UnauthorizedException 현재 사용자에게 권한이 없는 경우
     */
    override fun execute(
        faqId: UUID,
        updateFaqRequest: UpdateFaqRequest,
    ) {
        val faq = findFaqPort.findByIdOrNull(faqId) ?: throw FaqNotFoundException
        updateFaqRequest.run {
            faq.updateFaq(
                newTitle = title,
                newContent = content,
                newFaqType = faqType,
                newAdminId = userUtils.getCurrentUserId(),
            )
        }
    }
}
