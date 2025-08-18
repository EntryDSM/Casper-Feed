package hs.kr.entrydsm.feed.domain.attachFile.application.port.out

/**
 * 첨부 파일 삭제를 위한 포트 인터페이스입니다.
 * 첨부 파일을 삭제하는 메서드를 정의합니다.
 */
interface DeleteAttachFilePort {
    /**
     * 원본 첨부 파일명으로 첨부 파일을 삭제합니다.
     *
     * @param attachFileName 삭제할 첨부 파일명
     */
    fun deleteByOriginalAttachFileName(attachFileName: String)
}
