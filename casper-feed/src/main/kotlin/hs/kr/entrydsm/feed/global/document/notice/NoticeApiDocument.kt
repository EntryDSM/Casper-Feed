package hs.kr.entrydsm.feed.global.document.notice

import hs.kr.entrydsm.feed.domain.notice.adapter.`in`.web.dto.request.CreateNoticeRequest
import hs.kr.entrydsm.feed.domain.notice.adapter.`in`.web.dto.request.UpdateNoticeRequest
import hs.kr.entrydsm.feed.domain.notice.adapter.`in`.web.dto.response.QueryDetailsNoticeResponse
import hs.kr.entrydsm.feed.domain.notice.adapter.`in`.web.dto.response.QueryListNoticeResponse
import hs.kr.entrydsm.feed.domain.notice.adapter.`in`.web.dto.response.QueryNoticeTitleResponse
import hs.kr.entrydsm.feed.domain.notice.adapter.`in`.web.dto.response.UploadNoticeImageResponse
import hs.kr.entrydsm.feed.domain.notice.model.type.NoticeType
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.multipart.MultipartFile
import java.util.UUID

/**
 * Notice API 문서화를 위한 인터페이스입니다.
 */
@Tag(name = "Notice", description = "공지사항 API")
interface NoticeApiDocument {

    @Operation(
        summary = "공지사항 생성",
        description = "새로운 공지사항을 생성합니다."
    )
    @ApiResponses(
        ApiResponse(
            responseCode = "201",
            description = "공지사항 생성 성공",
            content = arrayOf(Content())
        ),
        ApiResponse(
            responseCode = "404",
            description = "AttachFile을 찾을 수 없습니다. - AttachFile Not Found",
            content = arrayOf(Content())
        )
    )
    /**
     * 새로운 공지사항을 생성합니다.
     *
     * @param createNoticeRequest 공지사항 생성 요청 데이터
     */
    fun createNotice(
        @RequestBody @Valid
        createNoticeRequest: CreateNoticeRequest,
    )

    @Operation(
        summary = "공지사항 수정",
        description = "공지사항을 수정합니다."
    )
    @ApiResponses(
        ApiResponse(
            responseCode = "200",
            description = "공지사항 수정 성공",
            content = arrayOf(Content())
        ),
        ApiResponse(
            responseCode = "404",
            description = "Notice를 찾을 수 없습니다. - Notice Not Found",
            content = arrayOf(Content())
        ),
        ApiResponse(
            responseCode = "404",
            description = "AttachFile을 찾을 수 없습니다. - AttachFile Not Found",
            content = arrayOf(Content())
        )
    )
    /**
     * 기존 공지사항을 수정합니다.
     *
     * @param id 수정할 공지사항의 ID
     * @param updateNoticeRequest 공지사항 수정 요청 데이터
     * @return 수정 결과 메시지
     */
    fun updateNotice(
        @PathVariable(name = "notice-id") id: UUID,
        @RequestBody updateNoticeRequest: UpdateNoticeRequest,
    ): ResponseEntity<String>

    @Operation(
        summary = "이미지 업로드",
        description = "공지사항에 첨부할 이미지를 업로드합니다."
    )
    @ApiResponses(
        ApiResponse(
            responseCode = "200",
            description = "이미지 업로드 성공",
            content = arrayOf(Content())
        )
    )
    /**
     * 공지사항에 첫부할 이미지를 업로드합니다.
     *
     * @param image 업로드할 이미지 파일
     * @return 업로드된 이미지 정보
     */
    fun uploadImage(
        @RequestPart(name = "photo") image: MultipartFile,
    ): UploadNoticeImageResponse

    @Operation(
        summary = "공지사항 제목 조회",
        description = "공지사항 제목을 조회합니다."
    )
    @ApiResponses(
        ApiResponse(
            responseCode = "200",
            description = "공지사항 제목 조회 성공",
            content = arrayOf(Content())
        )
    )
    /**
     * 모든 공지사항의 제목 목록을 조회합니다.
     *
     * @return 공지사항 제목 목록
     */
    fun queryNoticeTitle(): List<QueryNoticeTitleResponse>

    @Operation(
        summary = "공지사항 상세 조회",
        description = "공지사항을 상세 조회합니다."
    )
    @ApiResponses(
        ApiResponse(
            responseCode = "200",
            description = "공지사항 상세 조회 성공",
            content = arrayOf(Content())
        ),
        ApiResponse(
            responseCode = "404",
            description = "Notice를 찾을 수 없습니다. - Notice Not Found",
            content = arrayOf(Content())
        )
    )
    /**
     * 공지사항 상세 정보를 조회합니다.
     *
     * @param noticeId 조회할 공지사항의 ID
     * @return 공지사항 상세 정보
     */
    fun queryDetailsNotice(
        @PathVariable(name = "notice-id", required = true)
        noticeId: UUID,
    ): QueryDetailsNoticeResponse

    @Operation(
        summary = "종류별 공지사항 전체 조회",
        description = "종류별 공지사항을 전체 조회합니다."
    )
    @ApiResponses(
        ApiResponse(
            responseCode = "200",
            description = "종류별 공지사항 전체 조회 성공",
            content = arrayOf(Content())
        )
    )
    /**
     * 특정 유형의 공지사항 목록을 조회합니다.
     *
     * @param noticeType 조회할 공지사항 유형 (널 가능)
     * @return 공지사항 목록
     */
    fun queryNoticeListByType(
        @RequestParam("type") noticeType: NoticeType?,
    ): QueryListNoticeResponse

    @Operation(
        summary = "공지사항 삭제",
        description = "공지사항을 삭제합니다."
    )
    @ApiResponses(
        ApiResponse(
            responseCode = "200",
            description = "공지사항 삭제 성공",
            content = arrayOf(Content())
        )
        ,
        ApiResponse(
            responseCode = "404",
            description = "Notice를 찾을 수 없습니다. - Notice Not Found",
            content = arrayOf(Content())
        )
    )
    /**
     * 공지사항을 삭제합니다.
     *
     * @param id 삭제할 공지사항의 ID
     */
    fun deleteNotice(
        @PathVariable(name = "notice-id")id: UUID,
    )
}
