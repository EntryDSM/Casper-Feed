package hs.kr.entrydsm.feed.adapter.out.persistence.attachFile

import hs.kr.entrydsm.feed.adapter.out.mapper.attachFile.AttachFileMapper
import hs.kr.entrydsm.feed.adapter.out.persistence.attachFile.repository.AttachFileRepository
import hs.kr.entrydsm.feed.application.attachFile.port.out.DeleteAttachFilePort
import hs.kr.entrydsm.feed.application.attachFile.port.out.ExistsAttachFilePort
import hs.kr.entrydsm.feed.application.attachFile.port.out.FindAttachFilePort
import hs.kr.entrydsm.feed.application.attachFile.port.out.SaveAttachFilePort
import hs.kr.entrydsm.feed.model.attachFile.AttachFile
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
) : ExistsAttachFilePort, DeleteAttachFilePort, SaveAttachFilePort, FindAttachFilePort {
    override fun existsByOriginalAttachFileName(attachFileName: String): Boolean {
        return attachFileRepository.existsByOriginalAttachFileName(attachFileName)
    }

    override fun deleteByOriginalAttachFileName(attachFileName: String) {
        attachFileRepository.deleteByOriginalAttachFileName(attachFileName)
    }

    override fun save(attachFile: AttachFile): AttachFile {
        return attachFileMapper.toModel(attachFileRepository.save(attachFileMapper.toEntity(attachFile)))
    }

    override fun findByOriginalAttachFileName(attachFileName: String): List<AttachFile>? {
        return attachFileRepository.findByOriginalAttachFileName(attachFileName)?.map { attachFileMapper.toModel(it) }
    }
}
