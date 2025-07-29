package hs.kr.entrydsm.feed.adapter.out.persistence.notice

import hs.kr.entrydsm.feed.adapter.out.mapper.notice.NoticeMapper
import hs.kr.entrydsm.feed.adapter.out.persistence.notice.repository.NoticeRepository
import hs.kr.entrydsm.feed.application.notice.port.out.DeleteNoticePort
import hs.kr.entrydsm.feed.application.notice.port.out.FindNoticePort
import hs.kr.entrydsm.feed.application.notice.port.out.SaveNoticePort
import hs.kr.entrydsm.feed.model.notice.Notice
import hs.kr.entrydsm.feed.model.notice.type.NoticeType
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
) : SaveNoticePort, FindNoticePort, DeleteNoticePort {
    override fun saveNotice(notice: Notice) {
        noticeRepository.save(noticeMapper.toEntity(notice))
    }

    override fun findByIdOrNull(noticeId: UUID): Notice? =
        noticeRepository.findByIdOrNull(noticeId)?.let {
            noticeMapper.toModel(it)
        }

    override fun deleteNotice(notice: Notice) {
        noticeRepository.delete(noticeMapper.toEntity(notice))
    }

    override fun findAll(): List<Notice> {
        return noticeRepository.findAll().map { noticeMapper.toModel(it) }
    }

    override fun findAllByType(noticeType: NoticeType): List<Notice> {
        return noticeRepository.findAllByType(noticeType).map { noticeMapper.toModel(it) }
    }
}
