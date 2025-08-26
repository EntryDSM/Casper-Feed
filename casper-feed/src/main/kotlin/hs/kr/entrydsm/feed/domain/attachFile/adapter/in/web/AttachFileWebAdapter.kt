package hs.kr.entrydsm.feed.domain.attachFile.adapter.`in`.web

import hs.kr.entrydsm.feed.domain.attachFile.application.port.`in`.CreateAttachFileUseCase
import hs.kr.entrydsm.feed.global.document.attachFile.AttachFileApiDocument
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

/**
 * 첨부 파일 관련 HTTP 요청을 처리하는 웹 어댑터 클래스입니다.
 *
 * 이 클래스는 첨부 파일 업로드와 관련된 HTTP 엔드포인트를 제공하며,
 * 클라이언트의 요청을 적절한 서비스 메서드로 라우팅합니다.
 *
 * @property createAttachFileUseCase 첨부 파일 비즈니스 로직을 처리하는 서비스
 */
@RestController
@RequestMapping("/attach-file")
class AttachFileWebAdapter(
    private val createAttachFileUseCase: CreateAttachFileUseCase,
) : AttachFileApiDocument {
    /**
     * 하나 이상의 첨부 파일을 업로드하고, 업로드된 파일 정보를 반환합니다.
     *
     * 이 메서드는 클라이언트로부터 전송된 첨부 파일을 받아 서버에 저장하고,
     * 저장된 파일에 대한 정보(파일명, 다운로드 URL 등)를 반환합니다.
     *
     * @param attachFile 업로드할 첨부 파일 목록 (multipart/form-data 형식의 'attach_file' 파라미터로 전달)
     * @return 업로드된 첨부 파일 정보 목록 (CreateAttachFileResponse 리스트)
     *
     * @throws org.springframework.web.multipart.MultipartException 파일 업로드에 실패한 경우
     * @throws java.io.IOException 파일 저장 중 I/O 오류가 발생한 경우
     * @throws hs.kr.entrydsm.feed.global.error.exception.InternalServerErrorException 내부 서버 오류가 발생한 경우
     *
     * @see CreateAttachFileUseCase.execute
     */
    @PostMapping
    override fun createAttachFile(
        @RequestPart(value = "attach_file") attachFile: List<MultipartFile>,
    ) = createAttachFileUseCase.execute(attachFile)
}
