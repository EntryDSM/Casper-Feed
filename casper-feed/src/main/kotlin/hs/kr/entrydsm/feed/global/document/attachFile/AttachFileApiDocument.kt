package hs.kr.entrydsm.feed.global.document.attachFile

import hs.kr.entrydsm.feed.domain.attachFile.adapter.`in`.web.dto.response.CreateAttachFileResponse
import io.swagger.v3.oas.annotations.tags.Tag
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.multipart.MultipartFile

/**
 * AttachFile API 문서화를 위한 인터페이스입니다.
 */
@Tag(name = "AttachFile", description = "첨부 파일 API")
interface AttachFileApiDocument {

    @Operation(
        summary = "첨부 파일 업로드",
        description =
            "1. 이미 동일한 이름의 파일이 존재하면 삭제합니다.\n" +
            "2. 파일을 S3에 업로드합니다.\n" +
            "3. 업로드된 파일 정보를 데이터베이스에 저장합니다.\n" +
            "4. 업로드된 파일에 접근할 수 있는 URL을 생성하여 응답을 반환합니다."
    )
    @ApiResponses(
        ApiResponse(
            responseCode = "200",
            description = "파일 업로드 성공",
            content = arrayOf(Content())
        )
    )
    fun createAttachFile(
        @RequestPart(value = "attach_file") attachFile: List<MultipartFile>,
    ): List<CreateAttachFileResponse>
}
