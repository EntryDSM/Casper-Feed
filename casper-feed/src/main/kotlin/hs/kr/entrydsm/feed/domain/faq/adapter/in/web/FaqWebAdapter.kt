package hs.kr.entrydsm.feed.domain.faq.adapter.`in`.web

import hs.kr.entrydsm.feed.domain.faq.adapter.`in`.web.dto.request.CreateFaqRequest
import hs.kr.entrydsm.feed.domain.faq.adapter.`in`.web.dto.request.UpdateFaqRequest
import hs.kr.entrydsm.feed.domain.faq.adapter.`in`.web.dto.response.FaqDetailsResponse
import hs.kr.entrydsm.feed.domain.faq.adapter.`in`.web.dto.response.FaqListResponse
import hs.kr.entrydsm.feed.domain.faq.application.port.`in`.CreateFaqUseCase
import hs.kr.entrydsm.feed.domain.faq.application.port.`in`.DeleteFaqUseCase
import hs.kr.entrydsm.feed.domain.faq.application.port.`in`.QueryFaqDetailsUseCase
import hs.kr.entrydsm.feed.domain.faq.application.port.`in`.QueryFaqListByTypeUseCase
import hs.kr.entrydsm.feed.domain.faq.application.port.`in`.QueryFaqListUseCase
import hs.kr.entrydsm.feed.domain.faq.application.port.`in`.QueryTopFaqUseCase
import hs.kr.entrydsm.feed.domain.faq.application.port.`in`.UpdateFaqUseCase
import hs.kr.entrydsm.feed.domain.faq.model.type.FaqType
import hs.kr.entrydsm.feed.global.document.faq.FaqApiDocument
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

/**
 * FAQ 관련 HTTP 요청을 처리하는 웹 어댑터 클래스입니다.
 *
 * 이 클래스는 FAQ와 관련된 모든 HTTP 엔드포인트를 제공하며,
 * 클라이언트의 요청을 적절한 유스케이스로 라우팅합니다.
 *
 * @property createFaqUseCase FAQ 생성 유스케이스
 * @property deleteFaqUseCase FAQ 삭제 유스케이스
 * @property queryFaqDetailsUseCase FAQ 상세 정보 조회 유스케이스
 * @property queryFaqListByTypeUseCase FAQ 유형별 목록 조회 유스케이스
 * @property queryFaqListUseCase FAQ 전체 목록 조회 유스케이스
 * @property queryTopFaqUseCase 최근 FAQ 목록 조회 유스케이스
 * @property updateFaqUseCase FAQ 수정 유스케이스
 */
@RequestMapping("/faq")
@RestController
class FaqWebAdapter(
    private val createFaqUseCase: CreateFaqUseCase,
    private val deleteFaqUseCase: DeleteFaqUseCase,
    private val queryFaqDetailsUseCase: QueryFaqDetailsUseCase,
    private val queryFaqListByTypeUseCase: QueryFaqListByTypeUseCase,
    private val queryFaqListUseCase: QueryFaqListUseCase,
    private val queryTopFaqUseCase: QueryTopFaqUseCase,
    private val updateFaqUseCase: UpdateFaqUseCase,
) : FaqApiDocument {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    override fun createFaq(
        @RequestBody @Validated
        createFaqRequest: CreateFaqRequest,
    ) = createFaqUseCase.execute(createFaqRequest)

    /**
     * 특정 FAQ의 상세 정보를 조회합니다.
     *
     * @param faqId 조회할 FAQ의 고유 식별자
     * @return FAQ 상세 정보가 포함된 응답 객체
     */
    @GetMapping("/{faq-id}")
    override fun queryFaqDetails(
        @PathVariable("faq-id") faqId: UUID,
    ): FaqDetailsResponse = queryFaqDetailsUseCase.execute(faqId)

    /**
     * 특정 유형의 FAQ 목록을 조회합니다.
     *
     * @param faqType 조회할 FAQ 유형
     * @return 해당 유형의 FAQ 목록이 포함된 응답 객체
     */
    @GetMapping
    override fun queryFaqListByType(
        @RequestParam("type") faqType: FaqType,
    ): FaqListResponse = queryFaqListByTypeUseCase.execute(faqType)

    /**
     * 모든 FAQ 목록을 조회합니다.
     *
     * @return 모든 FAQ 목록이 포함된 응답 객체
     */
    @GetMapping("/all")
    override fun queryFaqList(): FaqListResponse = queryFaqListUseCase.execute()

    /**
     * 최근에 등록된 FAQ 목록을 조회합니다.
     *
     * @return 최근 FAQ 목록이 포함된 응답 객체
     */
    @GetMapping("/recently")
    override fun queryTopFaq() = queryTopFaqUseCase.execute()

    /**
     * 기존 FAQ를 수정합니다.
     *
     * @param faqId 수정할 FAQ의 고유 식별자
     * @param updateFaqRequest FAQ 수정 요청 데이터
     */
    @PatchMapping("/{faq-id}")
    override fun updateFaq(
        @PathVariable("faq-id") faqId: UUID,
        @RequestBody @Validated
        updateFaqRequest: UpdateFaqRequest,
    ) = updateFaqUseCase.execute(faqId, updateFaqRequest)

    /**
     * 특정 FAQ를 삭제합니다.
     *
     * @param faqId 삭제할 FAQ의 고유 식별자
     */
    @DeleteMapping("/{faq-id}")
    override fun deleteFaq(
        @PathVariable("faq-id") faqId: UUID,
    ) = deleteFaqUseCase.execute(faqId)
}
