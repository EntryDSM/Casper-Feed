package hs.kr.entrydsm.feed.global.document.screen

import hs.kr.entrydsm.feed.domain.screen.adatper.`in`.web.dto.response.QueryScreenResponse
import hs.kr.entrydsm.feed.domain.screen.adatper.`in`.web.dto.response.ScreenResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.multipart.MultipartFile
import java.util.UUID

/**
 * Screen API 문서화를 위한 인터페이스입니다.
 */
@Tag(name = "Screen", description = "전형 요강 API")
interface ScreenApiDocument {

    @Operation(
        summary = "전형 요강 이미지 업로드",
        description = "전형 요강 이미지를 업로드합니다."
    )
    @ApiResponses(
        ApiResponse(
            responseCode = "201",
            description = "전형 요강 이미지 업로드 성공",
            content = arrayOf(Content())
        ),
        ApiResponse(
            responseCode = "400",
            description = "파일이 비어있습니다.",
            content = arrayOf(Content())
        ),
        ApiResponse(
            responseCode = "400",
            description = "허용되지 않은 확장자입니다.",
            content = arrayOf(Content())
        ),
    )
    /**
     * 전형 요강 이미지를 새로 업로드합니다.
     *
     * @param image 업로드할 전형 요강 이미지 파일
     * @return 업로드된 이미지 정보
     */
    suspend fun createScreen(
        @RequestPart(name = "image") image: MultipartFile,
    ): ScreenResponse

    @Operation(
        summary = "전형 요강 이미지 수정",
        description = "전형 요강 이미지를 수정합니다."
    )
    @ApiResponses(
        ApiResponse(
            responseCode = "201",
            description = "전형 요강 이미지 수정 성공",
            content = arrayOf(Content())
        ),
        ApiResponse(
            responseCode = "400",
            description = "파일이 비어있습니다.",
            content = arrayOf(Content())
        ),
        ApiResponse(
            responseCode = "400",
            description = "허용되지 않은 확장자입니다.",
            content = arrayOf(Content())
        ),
    )
    /**
     * 기존 전형 요강 이미지를 수정합니다.
     *
     * @param id 수정할 전형 요강의 ID
     * @param image 새로 대체할 이미지 파일
     * @return 수정된 이미지 정보
     */
    fun updateScreen(
        @PathVariable(name = "screen-id") id: UUID,
        @RequestPart(name = "image") image: MultipartFile,
    ): ScreenResponse

    @Operation(
        summary = "전형 요강 이미지 조회",
        description = "전형 요강 이미지를 조회합니다."
    )
    @ApiResponses(
        ApiResponse(
            responseCode = "201",
            description = "전형 요강 이미지 조회 성공",
            content = arrayOf(Content())
        )
    )
    /**
     * 모든 전형 요강 이미지 목록을 조회합니다.
     *
     * @return 전형 요강 이미지 목록
     */
    fun queryScreen(): List<QueryScreenResponse>
}
