package hs.kr.entrydsm.feed.domain.notice.adapter.out.persistence

import hs.kr.entrydsm.feed.domain.notice.adapter.out.mapper.NoticeMapper
import hs.kr.entrydsm.feed.domain.notice.adapter.out.persistence.repository.NoticeRepository
import hs.kr.entrydsm.feed.domain.notice.application.port.out.NoticePort
import hs.kr.entrydsm.feed.domain.notice.model.Notice
import hs.kr.entrydsm.feed.domain.notice.model.type.NoticeType
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.util.UUID

/**
 * 공지사항 도메인과 데이터베이스 간의 상호작용을 담당하는 어댑터 클래스입니다.
 *
 * @property noticeRepository 공지사항 엔티티를 데이터베이스에서 조작하기 위한 리포지토리
 * @property noticeMapper 공지사항 도메인 객체와 엔티티 간의 변환을 담당하는 매퍼
 */
@Component
class NoticePersistenceAdapter(
    private val noticeRepository: NoticeRepository,
    private val noticeMapper: NoticeMapper,
) : NoticePort {

    /**
     * 공지사항을 저장하거나 업데이트합니다.
     *
     * @param notice 저장 또는 업데이트할 공지사항 도메인 객체
     */
    override fun saveNotice(notice: Notice) {
        noticeRepository.save(noticeMapper.toEntity(notice))
    }

    /**
     * 주어진 ID에 해당하는 공지사항을 조회합니다.
     *
     * @param noticeId 조회할 공지사항의 고유 식별자
     * @return 조회된 공지사항 도메인 객체, 없을 경우 null 반환
     */
    override fun findByIdOrNull(noticeId: UUID): Notice? =
        noticeRepository.findByIdOrNull(noticeId)?.let {
            noticeMapper.toModel(it)
        }

    /**
     * 공지사항을 삭제합니다.
     *
     * @param notice 삭제할 공지사항 도메인 객체
     */
    override fun deleteNotice(notice: Notice) {
        noticeRepository.delete(noticeMapper.toEntity(notice))
    }

    /**
     * 모든 공지사항을 조회합니다.
     *
     * @return 모든 공지사항 도메인 객체 목록 (없을 경우 빈 목록 반환)
     */
    override fun findAll(): List<Notice> {
        return noticeRepository.findAll().map { noticeMapper.toModel(it) }
    }

    /**
     * 지정된 유형의 모든 공지사항을 조회합니다.
     *
     * @param noticeType 조회할 공지사항의 유형 (GUIDE 또는 NOTICE)
     * @return 지정된 유형의 공지사항 도메인 객체 목록 (없을 경우 빈 목록 반환)
     */
    override fun findAllByType(noticeType: NoticeType): List<Notice> {
        return noticeRepository.findAllByType(noticeType).map { noticeMapper.toModel(it) }
    }
}
