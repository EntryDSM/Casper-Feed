package hs.kr.entrydsm.feed.adapter.`in`.notice.dto.response

/**
 * 공지사항 목록 조회 응답을 위한 데이터 클래스입니다.
 * 여러 개의 공지사항 정보를 리스트 형태로 반환할 때 사용됩니다.
 *
 * @property notices 공지사항 정보 목록 (NoticeResponse 리스트)
 */
data class QueryListNoticeResponse(
    val notices: List<NoticeResponse>,
)
