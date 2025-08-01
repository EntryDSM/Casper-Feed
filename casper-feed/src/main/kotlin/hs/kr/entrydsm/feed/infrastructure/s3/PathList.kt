package hs.kr.entrydsm.feed.infrastructure.s3

/**
 * S3 버킷 내의 디렉토리 경로를 관리하는 객체입니다.
 * 각 상수는 S3 버킷 내의 특정 디렉토리 경로를 나타냅니다.
 */
object PathList {
    /**
     * 공지사항 이미지 파일이 저장되는 디렉토리 경로입니다.
     */
    const val NOTICE = "notice/"

    /**
     * 화면 이미지 파일이 저장되는 디렉토리 경로입니다.
     */
    const val SCREEN = "screen/"

    /**
     * 첨부 파일이 저장되는 디렉토리 경로입니다.
     */
    const val ATTACH_FILE = "attach_file/"
}
