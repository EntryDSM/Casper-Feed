package hs.kr.entrydsm.feed.application.screen.service

import hs.kr.entrydsm.feed.adapter.`in`.screen.dto.response.ScreenResponse
import hs.kr.entrydsm.feed.application.screen.exception.ScreenNotFoundException
import hs.kr.entrydsm.feed.application.screen.port.`in`.UpdateScreenUseCase
import hs.kr.entrydsm.feed.application.screen.port.out.FindScreenPort
import hs.kr.entrydsm.feed.infrastructure.s3.PathList
import hs.kr.entrydsm.feed.infrastructure.s3.util.FileUtil
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.UUID

/**
 * 화면 이미지 업데이트를 처리하는 서비스 클래스입니다.
 *
 * @property fileUtil 파일 업로드 및 삭제를 위한 유틸리티
 * @property findScreenPort 화면 조회를 위한 포트
 */
@Transactional
@Service
class UpdateScreenService(
    private val fileUtil: FileUtil,
    private val findScreenPort: FindScreenPort,
) : UpdateScreenUseCase {
    /**
     * 지정된 ID의 화면 이미지를 새로운 이미지로 업데이트합니다.
     * 기존 이미지는 삭제됩니다.
     *
     * @param screenId 업데이트할 화면의 고유 식별자
     * @param file 새로운 이미지 파일
     * @return 업데이트된 화면 이미지의 URL이 포함된 응답
     * @throws hs.kr.entrydsm.feed.application.screen.exception.ScreenNotFoundException 지정된 ID의 화면을 찾을 수 없는 경우
     */
    override fun execute(
        screenId: UUID,
        file: MultipartFile,
    ): ScreenResponse {
        val screen =
            findScreenPort.findByIdOrNull(screenId)
                ?: throw ScreenNotFoundException
        val fileName = fileUtil.upload(file, PathList.SCREEN)
        fileUtil.delete(screen.image, PathList.SCREEN)
        screen.updateImage(fileName)

        return ScreenResponse(fileUtil.generateObjectUrl(fileName, PathList.SCREEN))
    }
}
