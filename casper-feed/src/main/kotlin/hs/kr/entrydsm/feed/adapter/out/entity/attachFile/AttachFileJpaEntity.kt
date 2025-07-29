package hs.kr.entrydsm.feed.adapter.out.entity.attachFile

import jakarta.persistence.Entity
import jakarta.persistence.Id

/**
 * 첨부 파일 정보를 데이터베이스에 저장하기 위한 JPA 엔티티 클래스입니다.
 *
 * @property uploadedFileName AWS S3에 업로드된 파일명 (UUID 등으로 인코딩된 파일명)
 * @property originalAttachFileName 원본 첨부 파일명 (사용자가 업로드한 원본 파일명)
 */
@Entity(name = "tbl_attach_file")
class AttachFileJpaEntity(
    @Id
    val uploadedFileName: String, // aws s3에 올라가는 fileName
    var originalAttachFileName: String, // 인코딩 되기 전 첨부파일 이름 ex): 서프수행.hwp
)
