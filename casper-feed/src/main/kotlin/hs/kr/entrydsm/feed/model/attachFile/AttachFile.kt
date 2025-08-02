package hs.kr.entrydsm.feed.model.attachFile

/**
 * 첨부 파일 정보를 나타내는 데이터 클래스입니다.
 * 이 클래스는 업로드된 파일의 시스템 내부 이름과 원본 파일 이름을 관리합니다.
 *
 * @property uploadedFileName 시스템에 저장된 파일 이름 (UUID 등으로 생성된 고유한 이름)
 * @property originalAttachFileName 사용자가 업로드한 원본 파일 이름
 */
data class AttachFile(
    val uploadedFileName: String,
    val originalAttachFileName: String,
)
