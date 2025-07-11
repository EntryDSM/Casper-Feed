package hs.kr.entrydsm.feed.domain.notice.exception

import hs.kr.entrydsm.feed.global.error.exception.EquusException
import hs.kr.entrydsm.feed.global.error.exception.ErrorCode

object AttachFileNotFoundException : EquusException(
    ErrorCode.ATTACH_FILE_NOT_FOUND
)
