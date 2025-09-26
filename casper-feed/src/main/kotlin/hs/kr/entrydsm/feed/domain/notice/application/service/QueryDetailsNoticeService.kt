package hs.kr.entrydsm.feed.domain.notice.application.service

import hs.kr.entrydsm.feed.domain.notice.adapter.`in`.web.dto.response.AttachFileElement
import hs.kr.entrydsm.feed.domain.notice.adapter.`in`.web.dto.response.QueryDetailsNoticeResponse
import hs.kr.entrydsm.feed.domain.notice.exception.NoticeNotFoundException
import hs.kr.entrydsm.feed.domain.notice.application.port.`in`.QueryDetailsNoticeUseCase
import hs.kr.entrydsm.feed.domain.notice.application.port.out.FindNoticePort
import hs.kr.entrydsm.feed.infrastructure.s3.PathList
import hs.kr.entrydsm.feed.infrastructure.s3.util.FileUtil
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

/**
 * 공지사항 상세 조회를 처리하는 서비스 클래스입니다.
 *
 * @property findNoticePort 공지사항 조회를 위한 포트
 * @property fileUtil 파일 URL 생성을 위한 유틸리티
 */
@Service
class QueryDetailsNoticeService(
    private val findNoticePort: FindNoticePort,
    private val fileUtil: FileUtil,
) : QueryDetailsNoticeUseCase {
    /**
     * 공지사항의 상세 정보를 조회합니다.
     *
     * @param noticeId 조회할 공지사항의 고유 식별자
     * @return 공지사항 상세 정보 응답
     * @throws NoticeNotFoundException 지정된 ID의 공지사항을 찾을 수 없는 경우
     */
    @Transactional(readOnly = true)
    override fun execute(noticeId: UUID): QueryDetailsNoticeResponse {
        val notice = findNoticePort.findByIdOrNull(noticeId) ?: throw NoticeNotFoundException
        val imageURL = notice.fileName?.let { getUrl(it, PathList.NOTICE) }

        val attachFile =
            notice.attachFile?.map {
                // 첨부파일 이름과 url을 묶어서 여러개 반환하기때문에 List로 묶는다
                AttachFileElement(
                    attachFileUrl = getUrl(it.uploadedFileName, PathList.ATTACH_FILE),
                    attachFileName = it.originalAttachFileName,
                )
            }

        return notice.run {
            QueryDetailsNoticeResponse(
                title = title,
                content = content,
                createdAt = createdAt,
                type = type,
                imageURL = imageURL,
                imageName = fileName,
                attachFiles = attachFile!!,
                isPinned = isPinned,
            )
        }
    }

    /**
     * 파일 경로와 파일명으로 URL을 생성합니다.
     *
     * @param file 파일명
     * @param path 파일 경로
     * @return 생성된 파일 URL
     */
    private fun getUrl(
        file: String,
        path: String,
    ) = fileUtil.generateObjectUrl(file, path)
}
