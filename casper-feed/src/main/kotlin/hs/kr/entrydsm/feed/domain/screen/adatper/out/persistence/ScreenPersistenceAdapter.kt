package hs.kr.entrydsm.feed.domain.screen.adatper.out.persistence

import hs.kr.entrydsm.feed.domain.screen.adatper.out.mapper.ScreenMapper
import hs.kr.entrydsm.feed.domain.screen.adatper.out.persistence.repository.ScreenRepository
import hs.kr.entrydsm.feed.domain.screen.application.port.out.ScreenPort
import hs.kr.entrydsm.feed.domain.screen.model.Screen
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
) : ScreenPort {

    /**
     * 화면 정보를 데이터베이스에 저장합니다.
     *
     * @param screen 저장할 화면 정보 도메인 객체
     */
    override fun saveScreen(screen: Screen) {
        screenRepository.save(screenMapper.toEntity(screen))
    }

    /**
     * 데이터베이스에 저장된 모든 화면 정보를 조회합니다.
     *
     * @return 모든 화면 정보 도메인 객체 리스트
     */
    override fun findAll(): List<Screen> {
        return screenRepository.findAll().map { screenMapper.toModel(it) }
    }

    /**
     * 고유 식별자(ID)로 화면 정보를 조회합니다.
     *
     * @param screenId 조회할 화면의 고유 식별자
     * @return 조회된 화면 정보 도메인 객체. 존재하지 않으면 null을 반환합니다.
     */
    override fun findByIdOrNull(screenId: UUID): Screen? =
        screenRepository.findByIdOrNull(screenId)?.let {
            screenMapper.toModel(it)
        }
}
