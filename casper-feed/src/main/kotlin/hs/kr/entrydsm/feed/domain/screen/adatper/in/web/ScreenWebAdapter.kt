package hs.kr.entrydsm.feed.domain.screen.adatper.`in`.web

import hs.kr.entrydsm.feed.domain.screen.adatper.`in`.web.dto.response.QueryScreenResponse
import hs.kr.entrydsm.feed.domain.screen.adatper.`in`.web.dto.response.ScreenResponse
import hs.kr.entrydsm.feed.domain.screen.application.port.`in`.CreateScreenUseCase
import hs.kr.entrydsm.feed.domain.screen.application.port.`in`.QueryScreenUseCase
import hs.kr.entrydsm.feed.domain.screen.application.port.`in`.UpdateScreenUseCase
import hs.kr.entrydsm.feed.global.document.screen.ScreenApiDocument
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.util.UUID

/**
 * 화면 정보 관련 HTTP 요청을 처리하는 웹 어댑터 클래스입니다.
 *
 * 이 클래스는 화면 정보와 관련된 모든 HTTP 엔드포인트를 제공하며,
 * 클라이언트의 요청을 적절한 유스케이스로 라우팅합니다.
 *
 * @property createScreenUseCase 화면 생성 유스케이스
 * @property updateScreenUseCase 화면 수정 유스케이스
 * @property queryScreenUseCase 화면 조회 유스케이스
 */
@RestController
@RequestMapping("/screen")
class ScreenWebAdapter(
    private val createScreenUseCase: CreateScreenUseCase,
    private val updateScreenUseCase: UpdateScreenUseCase,
    private val queryScreenUseCase: QueryScreenUseCase,
) : ScreenApiDocument {
    /**
     * 새로운 화면 이미지를 업로드하고 저장합니다.
     *
     * @param image 업로드할 화면 이미지 파일
     * @return 업로드된 화면 이미지 정보가 포함된 응답 객체
     */
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    override suspend fun createScreen(
        @RequestPart(name = "image") image: MultipartFile,
    ): ScreenResponse = createScreenUseCase.execute(image)

    /**
     * 기존 화면 이미지를 새로운 이미지로 업데이트합니다.
     *
     * @param id 업데이트할 화면의 고유 식별자
     * @param image 새로운 화면 이미지 파일
     * @return 업데이트된 화면 이미지 정보가 포함된 응답 객체
     */
    @PatchMapping("/{screen-id}")
    override fun updateScreen(
        @PathVariable(name = "screen-id") id: UUID,
        @RequestPart(name = "image") image: MultipartFile,
    ): ScreenResponse = updateScreenUseCase.execute(id, image)

    /**
     * 모든 화면 이미지 목록을 조회합니다.
     *
     * @return 화면 이미지 목록이 포함된 응답 객체 리스트
     */
    @GetMapping
    override fun queryScreen(): List<QueryScreenResponse> = queryScreenUseCase.execute()
}
