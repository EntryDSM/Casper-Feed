package hs.kr.entrydsm.feed.application.attachFile.port.out

/**
 * 첨부 파일 존재 여부 확인을 위한 포트 인터페이스입니다.
 * 첨부 파일의 존재 여부를 확인하는 메서드를 정의합니다.
 */
interface ExistsAttachFilePort {
    /**
     * 원본 첨부 파일명으로 첨부 파일의 존재 여부를 확인합니다.
     *
     * @param attachFileName 확인할 첨부 파일명
     * @return 파일이 존재하면 true, 그렇지 않으면 false
     */
    fun existsByOriginalAttachFileName(attachFileName: String): Boolean
}
