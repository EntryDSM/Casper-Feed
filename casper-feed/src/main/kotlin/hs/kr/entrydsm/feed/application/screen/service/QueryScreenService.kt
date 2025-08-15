package hs.kr.entrydsm.feed.application.screen.service

import hs.kr.entrydsm.feed.adapter.`in`.screen.dto.response.QueryScreenResponse
import hs.kr.entrydsm.feed.application.screen.port.`in`.QueryScreenUseCase
import hs.kr.entrydsm.feed.application.screen.port.out.FindScreenPort
import hs.kr.entrydsm.feed.infrastructure.s3.PathList
import hs.kr.entrydsm.feed.infrastructure.s3.util.FileUtil
import org.springframework.stereotype.Service

/**
 * 화면 이미지 목록 조회를 처리하는 서비스 클래스입니다.
 *
 * @property findScreenPort 화면 조회를 위한 포트
 * @property fileUtil 파일 URL 생성을 위한 유틸리티
 */
@Service
class QueryScreenService(
    private val findScreenPort: FindScreenPort,
    private val fileUtil: FileUtil,
) : QueryScreenUseCase {
    /**
     * 모든 화면 이미지 목록을 조회합니다.
     *
     * @return 화면 이미지 목록 (각 항목은 ID, URL, 생성일시, 수정일시 포함)
     */
    override fun execute(): List<QueryScreenResponse> {
        return findScreenPort.findAll()
            .map { it ->
                QueryScreenResponse(
                    it.id!!,
                    fileUtil.generateObjectUrl(it.image, PathList.SCREEN),
                    it.createdAt,
                    it.modifiedAt,
                )
            }
    }
}
