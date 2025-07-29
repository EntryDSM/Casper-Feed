package hs.kr.entrydsm.feed.application.attachFile.service

import hs.kr.entrydsm.feed.adapter.`in`.attachFile.dto.response.CreateAttachFileResponse
import hs.kr.entrydsm.feed.application.attachFile.port.`in`.CreateAttachFileUseCase
import hs.kr.entrydsm.feed.application.attachFile.port.out.DeleteAttachFilePort
import hs.kr.entrydsm.feed.application.attachFile.port.out.ExistsAttachFilePort
import hs.kr.entrydsm.feed.application.attachFile.port.out.SaveAttachFilePort
import hs.kr.entrydsm.feed.infrastructure.s3.PathList
import hs.kr.entrydsm.feed.infrastructure.s3.util.FileUtil
import hs.kr.entrydsm.feed.model.attachFile.AttachFile
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

/**
 * 첨부 파일 도메인에 대한 비즈니스 로직을 처리하는 서비스 클래스입니다.
 *
 * 이 클래스는 첨부 파일과 관련된 모든 비즈니스 로직을 캡슐화하며,
 * 파일 업로드, 조회, 삭제 등의 기능을 제공합니다.
 *
 * @property fileUtil 파일 업로드/다운로드와 관련된 유틸리티
 * @property existsAttachFilePort 첨부 파일 존재 여부 확인을 위한 포트
 * @property deleteAttachFilePort 첨부 파일 삭제를 위한 포트
 * @property saveAttachFilePort 첨부 파일 저장을 위한 포트
 */
@Service
class CreateAttachFileService(
    private val fileUtil: FileUtil,
    private val existsAttachFilePort: ExistsAttachFilePort,
    private val deleteAttachFilePort: DeleteAttachFilePort,
    private val saveAttachFilePort: SaveAttachFilePort,
) : CreateAttachFileUseCase {
    override fun execute(attachFile: List<MultipartFile>): List<CreateAttachFileResponse> {
        val attachFileResponses = mutableListOf<CreateAttachFileResponse>()

        attachFile.forEach { file ->
            if (existsAttachFilePort.existsByOriginalAttachFileName(file.originalFilename!!)) {
                deleteAttachFilePort.deleteByOriginalAttachFileName(file.originalFilename!!)
            }
            val uploadedFilename = fileUtil.upload(file, PathList.ATTACH_FILE)
            val attachFile =
                AttachFile(
                    uploadedFileName = uploadedFilename,
                    originalAttachFileName = file.originalFilename!!,
                )
            saveAttachFilePort.save(attachFile)
            val url = fileUtil.generateObjectUrl(uploadedFilename, PathList.ATTACH_FILE)
            attachFileResponses.add(CreateAttachFileResponse(file.originalFilename!!, url))
        }
        return attachFileResponses
    }
}
