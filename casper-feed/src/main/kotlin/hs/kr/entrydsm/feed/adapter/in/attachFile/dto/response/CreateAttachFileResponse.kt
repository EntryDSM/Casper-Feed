package hs.kr.entrydsm.feed.adapter.`in`.attachFile.dto.response

/**
 * 첨부 파일 생성 응답을 위한 데이터 클래스입니다.
 *
 * @property fileName 원본 파일 이름
 * @property url 업로드된 파일에 접근할 수 있는 URL
 */
class CreateAttachFileResponse(
    val fileName: String,
    val url: String,
)
