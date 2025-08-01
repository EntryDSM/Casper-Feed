package hs.kr.entrydsm.feed.application.notice.service

import hs.kr.entrydsm.feed.adapter.`in`.notice.dto.response.NoticeResponse
import hs.kr.entrydsm.feed.adapter.`in`.notice.dto.response.QueryListNoticeResponse
import hs.kr.entrydsm.feed.application.notice.port.`in`.QueryNoticeListByTypeUseCase
import hs.kr.entrydsm.feed.application.notice.port.out.FindNoticePort
import hs.kr.entrydsm.feed.model.notice.Notice
import hs.kr.entrydsm.feed.model.notice.type.NoticeType
import org.springframework.stereotype.Service

/**
 * 유형별 공지사항 목록 조회를 처리하는 서비스 클래스입니다.
 *
 * @property findNoticePort 공지사항 조회를 위한 포트
 */
@Service
class QueryNoticeListByTypeService(
    private val findNoticePort: FindNoticePort,
) : QueryNoticeListByTypeUseCase {
    /**
     * 공지사항 목록을 조회합니다. 유형별로 필터링할 수 있습니다.
     *
     * @param noticeType 조회할 공지사항 유형 (null인 경우 모든 유형 조회)
     * @return 공지사항 목록 응답 (고정 공지가 상단에 정렬됨)
     */
    override fun execute(noticeType: NoticeType?): QueryListNoticeResponse {
        val notices =
            getNoticeList(noticeType).map { it ->
                NoticeResponse(
                    id = it.id!!,
                    title = it.title,
                    isPinned = it.isPinned,
                    type = it.type,
                    createdAt = it.createdAt,
                )
            }.sortedWith(
                compareByDescending<NoticeResponse> { it.isPinned }
                    .thenByDescending { it.createdAt },
            )

        return QueryListNoticeResponse(notices)
    }

    /**
     * 공지사항 목록을 조회합니다. 유형별로 필터링할 수 있습니다.
     *
     * @param noticeType 조회할 공지사항 유형 (null인 경우 모든 유형 조회)
     * @return 공지사항 목록
     */
    private fun getNoticeList(noticeType: NoticeType?): List<Notice> {
        return noticeType?.let { findNoticePort.findAllByType(it) } ?: findNoticePort.findAll()
    }
}
