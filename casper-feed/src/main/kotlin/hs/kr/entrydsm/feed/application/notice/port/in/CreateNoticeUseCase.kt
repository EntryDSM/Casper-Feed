package hs.kr.entrydsm.feed.application.notice.port.`in`

import hs.kr.entrydsm.feed.adapter.`in`.notice.dto.request.CreateNoticeRequest

/**
 * 공지사항 생성을 위한 유스케이스 인터페이스입니다.
 * 공지사항 도메인에서 새로운 공지사항을 생성하는 역할을 담당합니다.
 */
interface CreateNoticeUseCase {
    /**
     * 새로운 공지사항을 생성합니다.
     *
     * @param request 공지사항 생성 요청 데이터
     */
    fun execute(request: CreateNoticeRequest)
}
