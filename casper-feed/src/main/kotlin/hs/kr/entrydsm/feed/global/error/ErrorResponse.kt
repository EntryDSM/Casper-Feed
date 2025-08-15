package hs.kr.entrydsm.feed.global.error

/**
 * 클라이언트에게 반환되는 오류 응답을 나타내는 데이터 클래스입니다.
 * HTTP 상태 코드와 오류 메시지를 포함합니다.
 *
 * @property status HTTP 상태 코드
 * @property message 사용자에게 표시할 오류 메시지 (선택 사항)
 */
data class ErrorResponse(
    val status: Int,
    val message: String?,
)
