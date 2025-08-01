package hs.kr.entrydsm.feed.adapter.out.persistence.screen

import hs.kr.entrydsm.feed.adapter.out.mapper.screen.ScreenMapper
import hs.kr.entrydsm.feed.adapter.out.persistence.screen.repository.ScreenRepository
import hs.kr.entrydsm.feed.application.screen.port.out.FindScreenPort
import hs.kr.entrydsm.feed.application.screen.port.out.SaveScreenPort
import hs.kr.entrydsm.feed.model.screen.Screen
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.util.UUID

/**
 * 화면 정보 도메인과 데이터베이스 간의 상호작용을 담당하는 어댑터 클래스입니다.
 *
 * @property screenRepository 화면 정보 엔티티를 데이터베이스에서 조작하기 위한 리포지토리
 * @property screenMapper 화면 정보 도메인 객체와 엔티티 간의 변환을 담당하는 매퍼
 */
@Component
class ScreenPersistenceAdapter(
    private val screenRepository: ScreenRepository,
    private val screenMapper: ScreenMapper,
) : SaveScreenPort, FindScreenPort {
    override fun saveScreen(screen: Screen) {
        screenRepository.save(screenMapper.toEntity(screen))
    }

    override fun findAll(): List<Screen> {
        return screenRepository.findAll().map { screenMapper.toModel(it) }
    }

    override fun findByIdOrNull(screenId: UUID): Screen? =
        screenRepository.findByIdOrNull(screenId)?.let {
            screenMapper.toModel(it)
        }
}
