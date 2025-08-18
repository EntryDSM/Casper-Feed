package hs.kr.entrydsm.feed.domain.attachFile.adapter.out.persistence

import hs.kr.entrydsm.feed.domain.attachFile.adapter.out.mapper.AttachFileMapper
import hs.kr.entrydsm.feed.domain.attachFile.adapter.out.persistence.repository.AttachFileRepository
import hs.kr.entrydsm.feed.domain.attachFile.application.port.out.AttachFilePort
import hs.kr.entrydsm.feed.domain.attachFile.model.AttachFile
import org.springframework.stereotype.Component

/**
 * 첨부 파일 도메인과 데이터베이스 간의 상호작용을 담당하는 어댑터 클래스입니다.
 * 첨부 파일의 CRUD 작업을 처리하며, 파일명을 통한 조회 및 삭제 기능을 제공합니다.
 *
 * @property attachFileRepository 첨부 파일 엔티티를 데이터베이스에서 조작하기 위한 리포지토리
 * @property attachFileMapper 첨부 파일 도메인 객체와 엔티티 간의 변환을 담당하는 매퍼
 */
@Component
class AttachFilePersistenceAdapter(
    private val attachFileRepository: AttachFileRepository,
    private val attachFileMapper: AttachFileMapper,
) : AttachFilePort {

    /**
     * 주어진 원본 파일명을 가진 첨부 파일이 존재하는지 확인합니다.
     *
     * @param attachFileName 확인할 원본 첨부 파일명
     * @return 파일이 존재하면 true, 그렇지 않으면 false 반환
     */
    override fun existsByOriginalAttachFileName(attachFileName: String): Boolean {
        return attachFileRepository.existsByOriginalAttachFileName(attachFileName)
    }

    /**
     * 주어진 원본 파일명을 가진 모든 첨부 파일을 삭제합니다.
     *
     * @param attachFileName 삭제할 첨부 파일의 원본 파일명
     */
    override fun deleteByOriginalAttachFileName(attachFileName: String) {
        attachFileRepository.deleteByOriginalAttachFileName(attachFileName)
    }

    /**
     * 첨부 파일을 저장하거나 업데이트합니다.
     *
     * @param attachFile 저장할 첨부 파일 도메인 객체
     * @return 저장된 첨부 파일 도메인 객체
     */
    override fun save(attachFile: AttachFile): AttachFile {
        return attachFileMapper.toModel(attachFileRepository.save(attachFileMapper.toEntity(attachFile)))
    }

    /**
     * 주어진 원본 파일명을 가진 모든 첨부 파일을 조회합니다.
     *
     * @param attachFileName 조회할 첨부 파일의 원본 파일명
     * @return 조회된 첨부 파일 도메인 객체 목록, 없을 경우 null 반환
     */
    override fun findByOriginalAttachFileName(attachFileName: String): List<AttachFile>? {
        return attachFileRepository.findByOriginalAttachFileName(attachFileName)?.map { attachFileMapper.toModel(it) }
    }
}
