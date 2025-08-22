package hs.kr.entrydsm.feed.domain.notice.application.service

import hs.kr.entrydsm.feed.domain.notice.adapter.`in`.web.dto.request.UpdateNoticeRequest
import hs.kr.entrydsm.feed.domain.attachFile.application.port.out.FindAttachFilePort
import hs.kr.entrydsm.feed.domain.notice.exception.AttachFileNotFoundException
import hs.kr.entrydsm.feed.domain.notice.exception.NoticeNotFoundException
import hs.kr.entrydsm.feed.global.utils.admin.AdminUtils
import hs.kr.entrydsm.feed.infrastructure.s3.PathList
import hs.kr.entrydsm.feed.infrastructure.s3.util.FileUtil
import hs.kr.entrydsm.feed.domain.attachFile.model.AttachFile
import hs.kr.entrydsm.feed.domain.notice.application.port.`in`.UpdateNoticeUseCase
import hs.kr.entrydsm.feed.domain.notice.application.port.out.FindNoticePort
import hs.kr.entrydsm.feed.domain.notice.application.port.out.SaveNoticePort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

/**
 * 공지사항 수정을 처리하는 서비스 클래스입니다.
 *
 * @property findNoticePort 공지사항 조회를 위한 포트
 * @property fileUtil 파일 업로드 유틸리티
 * @property adminUtils 관리자 인증 유틸리티
 * @property findAttachFilePort 첨부 파일 조회를 위한 포트
 */
@Service
class UpdateNoticeService(
    private val findNoticePort: FindNoticePort,
    private val fileUtil: FileUtil,
    private val adminUtils: AdminUtils,
    private val findAttachFilePort: FindAttachFilePort,
    private val saveNoticePort: SaveNoticePort
) : UpdateNoticeUseCase {
    /**
     * 공지사항을 수정합니다.
     *
     * @param noticeId 수정할 공지사항의 고유 식별자
     * @param request 공지사항 수정 요청 데이터
     * @return 수정된 이미지 URL이 포함된 응답 (이미지가 없는 경우 NO_CONTENT)
     * @throws NoticeNotFoundException 지정된 ID의 공지사항을 찾을 수 없는 경우
     * @throws AttachFileNotFoundException 첨부 파일을 찾을 수 없는 경우
     * @throws hs.kr.entrydsm.feed.global.exception.UnauthorizedException 관리자 인증에 실패한 경우
     */
    @Transactional
    override fun execute(
        noticeId: UUID,
        request: UpdateNoticeRequest,
    ): ResponseEntity<String> {
        val adminId = adminUtils.getCurrentAdminId()

        val notice = findNoticePort.findByIdOrNull(noticeId) ?: throw NoticeNotFoundException
        val fileName = request.fileName
        val attachFiles = findAttachFiles(request.attachFileName)

        saveNoticePort.saveNotice(
            request.run {
                notice.updateNotice(
                    newTitle = title,
                    newContent = content,
                    newIsPinned = isPinned,
                    newType = type,
                    newFileName = fileName,
                    newAdminId = adminId,
                    newAttachFile = attachFiles,
                )
            }
        )

        return fileName?.let {
            ResponseEntity.ok(fileUtil.generateObjectUrl(it, PathList.NOTICE))
        } ?: ResponseEntity(HttpStatus.NO_CONTENT)
    }

    /**
     * 파일명 목록으로 첨부 파일 목록을 조회합니다.
     *
     * @param fileNameList 조회할 파일명 목록 (null인 경우 빈 목록 반환)
     * @return 조회된 첨부 파일 목록
     * @throws AttachFileNotFoundException 첨부 파일을 찾을 수 없는 경우
     */
    private fun findAttachFiles(fileNameList: List<String>?): List<AttachFile> {
        return fileNameList?.flatMap { fileName ->
            val fileList = findAttachFilePort.findByOriginalAttachFileName(fileName)
            fileList ?: throw AttachFileNotFoundException
        } ?: emptyList()
    }
}
