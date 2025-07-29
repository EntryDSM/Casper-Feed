package hs.kr.entrydsm.feed.adapter.`in`.attachFile

import hs.kr.entrydsm.feed.application.attachFile.service.CreateAttachFileService
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
 * @property createAttachFileService 첨부 파일 비즈니스 로직을 처리하는 서비스
 */
@RestController
@RequestMapping("/attach-file")
class AttachFileWebAdapter(
    private val createAttachFileUseCase: CreateAttachFileService,
) {
    @PostMapping
    fun createAttachFile(
        @RequestPart(value = "attach_file") attachFile: List<MultipartFile>,
    ) = createAttachFileUseCase.execute(attachFile)
}
