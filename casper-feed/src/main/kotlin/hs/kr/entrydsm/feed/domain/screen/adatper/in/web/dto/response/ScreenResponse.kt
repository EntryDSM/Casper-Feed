package hs.kr.entrydsm.feed.domain.screen.adatper.`in`.web.dto.response

/**
 * 화면 정보 응답을 위한 데이터 클래스입니다.
 * 클라이언트에게 반환되는 화면 관련 정보를 담고 있습니다.
 *
 * @property image 화면에 표시될 이미지의 URL 또는 경로
 */
data class ScreenResponse(
    val image: String,
)
