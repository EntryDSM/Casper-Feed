package hs.kr.entrydsm.feed.application.attachFile.port.out

import hs.kr.entrydsm.feed.model.attachFile.AttachFile

/**
 * 첨부 파일 저장을 위한 포트 인터페이스입니다.
 * 첨부 파일 도메인 객체를 저장하는 메서드를 정의합니다.
 */
interface SaveAttachFilePort {
    /**
     * 첨부 파일을 저장하거나 업데이트합니다.
     *
     * @param attachFile 저장할 첨부 파일 도메인 객체
     * @return 저장된 첨부 파일 도메인 객체 (ID가 할당됨)
     */
    fun save(attachFile: AttachFile): AttachFile
}
