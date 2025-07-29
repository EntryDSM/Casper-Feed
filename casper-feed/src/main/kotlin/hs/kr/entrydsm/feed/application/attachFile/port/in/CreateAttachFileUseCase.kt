package hs.kr.entrydsm.feed.application.attachFile.port.`in`

import hs.kr.entrydsm.feed.adapter.`in`.attachFile.dto.response.CreateAttachFileResponse
import org.springframework.web.multipart.MultipartFile

/**
 * 첨부 파일 관련 비즈니스 로직을 정의한 인터페이스입니다.
 * 첨부 파일 업로드 기능을 제공합니다.
 */
interface CreateAttachFileUseCase {
    /**
     * 하나 이상의 첨부 파일을 업로드합니다.
     *
     * @param attachFile 업로드할 첨부 파일 목록
     * @return 생성된 첨부 파일 정보 응답 목록
     */
    fun execute(attachFile: List<MultipartFile>): List<CreateAttachFileResponse>
}
