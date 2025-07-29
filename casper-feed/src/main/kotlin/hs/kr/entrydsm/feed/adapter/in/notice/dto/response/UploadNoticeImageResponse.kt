package hs.kr.entrydsm.feed.adapter.`in`.notice.dto.response

/**
 * 공지사항 이미지 업로드 응답을 위한 데이터 클래스입니다.
 *
 * @property fileUrl 업로드된 이미지 파일에 접근할 수 있는 URL
 * @property fileName 업로드된 이미지 파일의 원본 이름
 */
data class UploadNoticeImageResponse(
    val fileUrl: String,
    val fileName: String,
)
