package hs.kr.entrydsm.feed.application.screen.port.`in`

import hs.kr.entrydsm.feed.adapter.`in`.screen.dto.response.ScreenResponse
import org.springframework.web.multipart.MultipartFile

/**
 * 화면 생성을 위한 유스케이스 인터페이스입니다.
 * 화면 도메인에서 새로운 화면 이미지를 업로드하고 생성하는 역할을 담당합니다.
 */
interface CreateScreenUseCase {

    suspend fun execute(file: MultipartFile): ScreenResponse
}
