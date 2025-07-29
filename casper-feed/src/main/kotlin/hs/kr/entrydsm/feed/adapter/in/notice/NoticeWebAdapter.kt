package hs.kr.entrydsm.feed.adapter.`in`.notice

import hs.kr.entrydsm.feed.adapter.`in`.notice.dto.request.CreateNoticeRequest
import hs.kr.entrydsm.feed.adapter.`in`.notice.dto.request.UpdateNoticeRequest
import hs.kr.entrydsm.feed.adapter.`in`.notice.dto.response.QueryDetailsNoticeResponse
import hs.kr.entrydsm.feed.adapter.`in`.notice.dto.response.QueryListNoticeResponse
import hs.kr.entrydsm.feed.adapter.`in`.notice.dto.response.QueryNoticeTitleResponse
import hs.kr.entrydsm.feed.adapter.`in`.notice.dto.response.UploadNoticeImageResponse
import hs.kr.entrydsm.feed.application.notice.port.`in`.CreateNoticeUseCase
import hs.kr.entrydsm.feed.application.notice.port.`in`.DeleteNoticeUseCase
import hs.kr.entrydsm.feed.application.notice.port.`in`.QueryDetailsNoticeUseCase
import hs.kr.entrydsm.feed.application.notice.port.`in`.QueryNoticeListByTypeUseCase
import hs.kr.entrydsm.feed.application.notice.port.`in`.QueryNoticeTitleUseCase
import hs.kr.entrydsm.feed.application.notice.port.`in`.UpdateNoticeUseCase
import hs.kr.entrydsm.feed.application.notice.port.`in`.UploadNoticeImageUseCase
import hs.kr.entrydsm.feed.model.notice.type.NoticeType
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.util.UUID

/**
 * 공지사항 관련 HTTP 요청을 처리하는 웹 어댑터 클래스입니다.
 *
 * 이 클래스는 공지사항과 관련된 모든 HTTP 엔드포인트를 제공하며,
 * 클라이언트의 요청을 적절한 서비스 메서드로 라우팅합니다.
 *
 * @property noticeService 공지사항 비즈니스 로직을 처리하는 서비스
 */
@RestController
@RequestMapping("/notice")
class NoticeWebAdapter(
    private val createNoticeUseCase: CreateNoticeUseCase,
    private val updateNoticeUseCase: UpdateNoticeUseCase,
    private val deleteNoticeUseCase: DeleteNoticeUseCase,
    private val queryDetailsNoticeUseCase: QueryDetailsNoticeUseCase,
    private val queryNoticeTitleUseCase: QueryNoticeTitleUseCase,
    private val uploadNoticeImageUseCase: UploadNoticeImageUseCase,
    private val queryListNoticeListByTypeUseCase: QueryNoticeListByTypeUseCase,
) {
    /**
     * 새로운 공지사항을 생성합니다.
     *
     * @param createNoticeRequest 공지사항 생성 요청 데이터
     */
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    fun createNotice(
        @RequestBody @Valid
        createNoticeRequest: CreateNoticeRequest,
    ) {
        createNoticeUseCase.execute(createNoticeRequest)
    }

    /**
     * 기존 공지사항을 수정합니다.
     *
     * @param id 수정할 공지사항의 고유 식별자
     * @param updateNoticeRequest 공지사항 수정 요청 데이터
     * @return 수정 결과에 대한 응답 엔티티
     */
    @PatchMapping("/{notice-id}")
    fun updateNotice(
        @PathVariable(name = "notice-id") id: UUID,
        @RequestBody updateNoticeRequest: UpdateNoticeRequest,
    ): ResponseEntity<String> = updateNoticeUseCase.execute(id, updateNoticeRequest)

    /**
     * 공지사항에 첨부할 이미지를 업로드합니다.
     *
     * @param image 업로드할 이미지 파일
     * @return 업로드된 이미지 정보가 포함된 응답 객체
     */
    @PostMapping("/image")
    fun uploadImage(
        @RequestPart(name = "photo") image: MultipartFile,
    ): UploadNoticeImageResponse = uploadNoticeImageUseCase.execute(image)

    /**
     * 모든 공지사항의 제목 목록을 조회합니다.
     *
     * @return 공지사항 제목 목록이 포함된 응답 객체 리스트
     */
    @GetMapping("/title")
    fun queryNoticeTitle(): List<QueryNoticeTitleResponse> = queryNoticeTitleUseCase.execute()

    /**
     * 특정 공지사항의 상세 정보를 조회합니다.
     *
     * @param noticeId 조회할 공지사항의 고유 식별자
     * @return 공지사항 상세 정보가 포함된 응답 객체
     */
    @GetMapping("/{notice-id}")
    fun queryDetailsNotice(
        @PathVariable(name = "notice-id", required = true)
        noticeId: UUID,
    ): QueryDetailsNoticeResponse = queryDetailsNoticeUseCase.execute(noticeId)

    /**
     * 특정 유형의 공지사항 목록을 조회합니다.
     *
     * @param noticeType 조회할 공지사항 유형 (선택 사항)
     * @return 해당 유형의 공지사항 목록이 포함된 응답 객체
     */
    @GetMapping
    fun queryNoticeListByType(
        @RequestParam("type") noticeType: NoticeType?,
    ): QueryListNoticeResponse = queryListNoticeListByTypeUseCase.execute(noticeType)

    /**
     * 특정 공지사항을 삭제합니다.
     *
     * @param id 삭제할 공지사항의 고유 식별자
     */
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/{notice-id}")
    fun deleteNotice(
        @PathVariable(name = "notice-id")id: UUID,
    ) = deleteNoticeUseCase.execute(id)
}
