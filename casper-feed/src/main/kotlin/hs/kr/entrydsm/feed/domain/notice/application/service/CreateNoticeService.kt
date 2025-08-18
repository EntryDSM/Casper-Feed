package hs.kr.entrydsm.feed.domain.notice.application.service

import hs.kr.entrydsm.feed.domain.notice.adapter.`in`.web.dto.request.CreateNoticeRequest
import hs.kr.entrydsm.feed.domain.attachFile.application.port.out.FindAttachFilePort
import hs.kr.entrydsm.feed.domain.notice.exception.AttachFileNotFoundException
import hs.kr.entrydsm.feed.domain.notice.application.port.`in`.CreateNoticeUseCase
import hs.kr.entrydsm.feed.domain.notice.application.port.out.SaveNoticePort
import hs.kr.entrydsm.feed.global.utils.admin.AdminUtils
import hs.kr.entrydsm.feed.domain.notice.model.Notice
import org.springframework.stereotype.Service

/**
 * 공지사항 생성을 처리하는 서비스 클래스입니다.
 *
 * @property adminUtils 관리자 인증 유틸리티
 * @property findAttachFilePort 첨부 파일 조회를 위한 포트
 * @property saveNoticePort 공지사항 저장을 위한 포트
 */
@Service
class CreateNoticeService(
    private val adminUtils: AdminUtils,
    private val findAttachFilePort: FindAttachFilePort,
    private val saveNoticePort: SaveNoticePort,
) : CreateNoticeUseCase {
    /**
     * 새로운 공지사항을 생성합니다.
     *
     * @param request 공지사항 생성 요청 데이터
     * @throws AttachFileNotFoundException 첨부 파일을 찾을 수 없는 경우
     * @throws hs.kr.entrydsm.feed.global.exception.UnauthorizedException 관리자 인증에 실패한 경우
     */
    override fun execute(request: CreateNoticeRequest) {
        val admin = adminUtils.getCurrentAdminId()
        val attachFiles =
            request.attachFileName?.let { fileNames ->
                fileNames.flatMap { fileName ->
                    val files = findAttachFilePort.findByOriginalAttachFileName(fileName)
                    files ?: throw AttachFileNotFoundException
                }
            } ?: emptyList()

        saveNoticePort.saveNotice(
            Notice(
                title = request.title,
                content = request.content,
                type = request.type,
                isPinned = request.isPinned,
                adminId = admin,
                fileName = request.fileName,
                attachFile = attachFiles,
            ),
        )
    }
}
