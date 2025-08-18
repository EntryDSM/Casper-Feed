package hs.kr.entrydsm.feed.domain.attachFile.application.port.out

import hs.kr.entrydsm.feed.domain.attachFile.model.AttachFile

/**
 * 첨부 파일 조회를 위한 포트 인터페이스입니다.
 * 첨부 파일 도메인 객체를 조회하는 메서드를 정의합니다.
 */
interface FindAttachFilePort {
    /**
     * 원본 첨부 파일명으로 첨부 파일 목록을 조회합니다.
     *
     * @param attachFileName 조회할 첨부 파일명
     * @return 조회된 첨부 파일 목록, 없을 경우 null
     */
    fun findByOriginalAttachFileName(attachFileName: String): List<AttachFile>?
}
