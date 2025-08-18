package hs.kr.entrydsm.feed.domain.screen.application.port.out

/**
 * 화면 이미지 관련 모든 포트 인터페이스를 통합한 포트입니다.
 * 화면 이미지 데이터의 CRUD 작업을 위한 모든 인터페이스를 상속받습니다.
 */
interface ScreenPort :
    FindScreenPort,
    SaveScreenPort
