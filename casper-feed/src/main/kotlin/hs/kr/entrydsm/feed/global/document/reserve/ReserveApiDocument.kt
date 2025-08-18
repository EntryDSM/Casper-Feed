package hs.kr.entrydsm.feed.global.document.reserve

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag

/**
 * Reserve API 문서화를 위한 인터페이스입니다.
 */
@Tag(name = "Reserve", description = "예약 링크 API")
interface ReserveApiDocument {

    @Operation(
        summary = "예약 페이지 링크 조회",
        description = "예약 페이지 링크를 조회합니다."
    )
    @ApiResponses(
        ApiResponse(
            responseCode = "200",
            description = "예약 페이지 링크 조회 성공",
            content = arrayOf(Content())
        )
    )
    fun reserveLink(): String
}
