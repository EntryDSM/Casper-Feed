package hs.kr.entrydsm.feed.application.notice.service

import hs.kr.entrydsm.feed.adapter.`in`.notice.dto.response.QueryNoticeTitleResponse
import hs.kr.entrydsm.feed.application.notice.port.`in`.QueryNoticeTitleUseCase
import hs.kr.entrydsm.feed.application.notice.port.out.FindNoticePort
import org.springframework.stereotype.Service

/**
 * 공지사항 제목 조회를 처리하는 서비스 클래스입니다.
 *
 * @property findNoticePort 공지사항 조회를 위한 포트
 */
@Service
class QueryNoticeTitleService(
    private val findNoticePort: FindNoticePort,
) : QueryNoticeTitleUseCase {
    /**
     * 모든 공지사항의 제목과 작성일을 조회합니다.
     *
     * @return 공지사항 제목 목록 (최신순으로 정렬)
     */
    override fun execute(): List<QueryNoticeTitleResponse> {
        return findNoticePort.findAll()
            .map {
                    it ->
                QueryNoticeTitleResponse(
                    id = it.id!!,
                    title = it.title,
                    it.createdAt,
                )
            }.sortedByDescending { it.createdAt }
    }
}
