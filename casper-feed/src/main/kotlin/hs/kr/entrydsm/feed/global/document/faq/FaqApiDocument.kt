package hs.kr.entrydsm.feed.global.document.faq

import hs.kr.entrydsm.feed.domain.faq.adapter.`in`.web.dto.request.CreateFaqRequest
import hs.kr.entrydsm.feed.domain.faq.adapter.`in`.web.dto.request.UpdateFaqRequest
import hs.kr.entrydsm.feed.domain.faq.adapter.`in`.web.dto.response.FaqDetailsResponse
import hs.kr.entrydsm.feed.domain.faq.adapter.`in`.web.dto.response.FaqListResponse
import hs.kr.entrydsm.feed.domain.faq.adapter.`in`.web.dto.response.FaqTitleResponse
import hs.kr.entrydsm.feed.domain.faq.model.type.FaqType
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import java.util.UUID

/**
 * Faq API 문서화를 위한 인터페이스입니다.
 */
@Tag(name = "Faq", description = "자주 묻는 질문 API")
interface FaqApiDocument {

    @Operation(
        summary = "Faq 생성",
        description = "새로운 FAQ를 생성합니다."
    )
    @ApiResponses(
        ApiResponse(
            responseCode = "201",
            description = "Faq 생성 성공",
            content = arrayOf(Content())
        )
    )

    /**
     * 새로운 FAQ를 생성합니다.
     *
     * @param createFaqRequest FAQ 생성 요청 데이터
     */
    fun createFaq(
        @RequestBody @Validated
        createFaqRequest: CreateFaqRequest,
    )

    @Operation(
        summary = "Faq 상세 조회",
        description = "FAQ를 상세 조회합니다."
    )
    @ApiResponses(
        ApiResponse(
            responseCode = "200",
            description = "Faq 상세 조회 성공",
            content = arrayOf(Content())
        ),
        ApiResponse(
            responseCode = "404",
            description = "Faq를 찾을 수 없습니다. - Faq Not Found",
            content = arrayOf(Content())
        )
    )
    /**
     * FAQ 상세 정보를 조회합니다.
     *
     * @param faqId 조회할 FAQ의 ID
     * @return FAQ 상세 정보
     */
    fun queryFaqDetails(
        @PathVariable("faq-id") faqId: UUID,
    ): FaqDetailsResponse

    @Operation(
        summary = "종류별 Faq 전체 조회",
        description = "종류별 FAQ를 전체 조회합니다."
    )
    @ApiResponses(
        ApiResponse(
            responseCode = "200",
            description = "종류별 Faq 전체 조회 성공",
            content = arrayOf(Content())
        )
    )
    /**
     * 특정 유형의 FAQ 목록을 조회합니다.
     *
     * @param faqType 조회할 FAQ 유형
     * @return FAQ 목록
     */
    fun queryFaqListByType(
        @RequestParam("type") faqType: FaqType,
    ): FaqListResponse

    @Operation(
        summary = "Faq 전체 조회",
        description = "FAQ를 전체 조회합니다."
    )
    @ApiResponses(
        ApiResponse(
            responseCode = "200",
            description = "Faq 전체 조회 성공",
            content = arrayOf(Content())
        )
    )
    /**
     * 모든 FAQ 목록을 조회합니다.
     *
     * @return 전체 FAQ 목록
     */
    fun queryFaqList(): FaqListResponse

    @Operation(
        summary = "상위 Faq 조회",
        description = "최근에 생성된 상위 5개의 FAQ를 조회합니다."
    )
    @ApiResponses(
        ApiResponse(
            responseCode = "200",
            description = "상위 Faq 조회 성공",
            content = arrayOf(Content())
        )
    )
    /**
     * 최근에 생성된 상위 5개의 FAQ 제목을 조회합니다.
     *
     * @return 상위 FAQ 제목 목록
     */
    fun queryTopFaq(): List<FaqTitleResponse>

    @Operation(
        summary = "Faq 수정",
        description = "FAQ를 수정합니다."
    )
    @ApiResponses(
        ApiResponse(
            responseCode = "200",
            description = "Faq 수정 성공",
            content = arrayOf(Content())
        ),
        ApiResponse(
            responseCode = "404",
            description = "Faq를 찾을 수 없습니다. - Faq Not Found",
            content = arrayOf(Content())
        )
    )
    /**
     * 기존 FAQ를 수정합니다.
     *
     * @param faqId 수정할 FAQ의 ID
     * @param updateFaqRequest FAQ 수정 요청 데이터
     */
    fun updateFaq(
        @PathVariable("faq-id") faqId: UUID,
        @RequestBody @Validated
        updateFaqRequest: UpdateFaqRequest,
    )

    @Operation(
        summary = "Faq 삭제",
        description = "FAQ를 삭제합니다."
    )
    @ApiResponses(
        ApiResponse(
            responseCode = "200",
            description = "Faq 삭제 성공",
            content = arrayOf(Content())
        ),
        ApiResponse(
            responseCode = "404",
            description = "Faq를 찾을 수 없습니다. - Faq Not Found",
            content = arrayOf(Content())
        )
    )
    /**
     * FAQ를 삭제합니다.
     *
     * @param faqId 삭제할 FAQ의 ID
     */
    fun deleteFaq(
        @PathVariable("faq-id") faqId: UUID,
    )
}
