package hs.kr.entrydsm.feed.model.screen

import java.time.LocalDateTime
import java.util.UUID

/**
 * 화면 정보를 나타내는 도메인 모델 클래스입니다.
 *
 * @property id 화면의 고유 식별자 (생성 시 자동 할당)
 * @property image 화면 이미지 파일명 또는 경로
 * @property adminId 화면을 등록한 관리자 ID
 * @property createdAt 화면 정보 생성 일시 (기본값: 현재 시간)
 * @property modifiedAt 화면 정보 수정 일시 (기본값: 현재 시간)
 */
data class Screen(
    val id: UUID? = null,
    val image: String,
    val adminId: UUID,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val modifiedAt: LocalDateTime = LocalDateTime.now(),
) {
    /**
     * 화면 이미지를 새로운 이미지로 업데이트합니다.
     *
     * @param newImage 새로운 화면 이미지 파일명 또는 경로
     * @return 이미지가 업데이트된 Screen 객체
     */
    fun updateImage(newImage: String): Screen =copy(image = newImage)
}
