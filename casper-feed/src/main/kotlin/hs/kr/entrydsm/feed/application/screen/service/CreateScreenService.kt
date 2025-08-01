package hs.kr.entrydsm.feed.application.screen.service

import hs.kr.entrydsm.feed.adapter.`in`.screen.dto.response.ScreenResponse
import hs.kr.entrydsm.feed.application.screen.port.`in`.CreateScreenUseCase
import hs.kr.entrydsm.feed.application.screen.port.out.SaveScreenPort
import hs.kr.entrydsm.feed.global.utils.admin.AdminUtils
import hs.kr.entrydsm.feed.infrastructure.s3.PathList
import hs.kr.entrydsm.feed.infrastructure.s3.util.FileUtil
import hs.kr.entrydsm.feed.model.screen.Screen
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

/**
 * 화면 이미지 생성을 처리하는 서비스 클래스입니다.
 *
 * @property saveScreenPort 화면 저장을 위한 포트
 * @property userUtils 사용자 인증 유틸리티
 * @property fileUtil 파일 업로드 유틸리티
 */
@Service
class CreateScreenService(
    private val saveScreenPort: SaveScreenPort,
    private val adminUtils: AdminUtils,
    private val fileUtil: FileUtil,
) : CreateScreenUseCase {

    override suspend fun execute(file: MultipartFile): ScreenResponse {
        val adminId = adminUtils.getCurrentAdmin().id

        val fileName = fileUtil.upload(file, PathList.SCREEN)
        saveScreenPort.saveScreen(
            Screen(
                image = fileName,
                adminId = adminId,
            ),
        )
        return ScreenResponse(fileName)
    }
}
