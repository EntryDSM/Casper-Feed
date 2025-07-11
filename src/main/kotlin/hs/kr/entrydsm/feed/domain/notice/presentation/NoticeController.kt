package hs.kr.entrydsm.feed.domain.notice.presentation

import hs.kr.entrydsm.feed.domain.notice.domain.type.NoticeType
import hs.kr.entrydsm.feed.domain.notice.presentation.dto.request.CreateNoticeRequest
import hs.kr.entrydsm.feed.domain.notice.presentation.dto.response.QueryListNoticeResponse
import hs.kr.entrydsm.feed.domain.notice.presentation.dto.request.UpdateNoticeRequest
import hs.kr.entrydsm.feed.domain.notice.presentation.dto.response.QueryDetailsNoticeResponse
import hs.kr.entrydsm.feed.domain.notice.presentation.dto.response.QueryNoticeTitleResponse
import hs.kr.entrydsm.feed.domain.notice.presentation.dto.response.UploadNoticeImageResponse
import hs.kr.entrydsm.feed.domain.notice.service.CreateNoticeService
import hs.kr.entrydsm.feed.domain.notice.service.DeleteNoticeService
import hs.kr.entrydsm.feed.domain.notice.service.QueryDetailsNoticeService
import hs.kr.entrydsm.feed.domain.notice.service.QueryNoticeListByTypeService
import hs.kr.entrydsm.feed.domain.notice.service.QueryNoticeTitleService
import hs.kr.entrydsm.feed.domain.notice.service.UpdateNoticeService
import hs.kr.entrydsm.feed.domain.notice.service.UploadNoticeImageService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.util.UUID

@RestController
@RequestMapping("/notice")
class NoticeController(
    private val createNoticeService: CreateNoticeService,
    private val uploadNoticeImageService: UploadNoticeImageService,
    private val updateNoticeService: UpdateNoticeService,
    private val queryNoticeTitleService: QueryNoticeTitleService,
    private val queryNoticeListByTypeService: QueryNoticeListByTypeService,
    private val queryDetailsNoticeService: QueryDetailsNoticeService,
    private val deleteNoticeService: DeleteNoticeService
) {

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    fun createNotice(
        @RequestBody @Valid
        createNoticeRequest: CreateNoticeRequest
    ) {
        createNoticeService.execute(createNoticeRequest)
    }

    @PatchMapping("/{notice-id}")
    fun modifyNotice(
        @PathVariable(name = "notice-id") id: UUID,
        @RequestBody updateNoticeRequest: UpdateNoticeRequest
    ): ResponseEntity<String> =
        updateNoticeService.execute(id, updateNoticeRequest)

    @PostMapping("/image")
    fun uploadImage(
        @RequestPart(name = "photo") image: MultipartFile
    ): UploadNoticeImageResponse =
        uploadNoticeImageService.execute(image)

    @GetMapping("/title")
    fun queryTitle(): List<QueryNoticeTitleResponse> = queryNoticeTitleService.execute()

    @GetMapping("/{notice-id}")
    fun getNotice(
        @PathVariable(name = "notice-id", required = true)
        noticeId: UUID
    ): QueryDetailsNoticeResponse = queryDetailsNoticeService.execute(noticeId)

    @GetMapping
    fun getNoticeListByType(
        @RequestParam("type") type: NoticeType?
    ): QueryListNoticeResponse =
        queryNoticeListByTypeService.execute(type)

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/{notice-id}")
    fun deleteNotice(
        @PathVariable(name = "notice-id")id: UUID
    ) =
        deleteNoticeService.execute(id)
}
