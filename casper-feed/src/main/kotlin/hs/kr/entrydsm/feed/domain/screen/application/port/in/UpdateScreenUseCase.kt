package hs.kr.entrydsm.feed.domain.screen.application.port.`in`

import hs.kr.entrydsm.feed.domain.screen.adatper.`in`.web.dto.response.ScreenResponse
import org.springframework.web.multipart.MultipartFile
import java.util.UUID

/**
 * 화면 업데이트를 위한 유스케이스 인터페이스입니다.
 * 화면 도메인에서 기존 화면 이미지를 새로운 이미지로 업데이트하는 역할을 담당합니다.
 */
interface UpdateScreenUseCase {
    /**
     * 특정 화면 이미지를 업데이트합니다.
     *
     * @param screenId 업데이트할 화면의 고유 식별자
     * @param file 새로운 화면 이미지 파일
     * @return 화면 업데이트 응답
     */
    fun execute(
        screenId: UUID,
        file: MultipartFile,
    ): ScreenResponse
}
