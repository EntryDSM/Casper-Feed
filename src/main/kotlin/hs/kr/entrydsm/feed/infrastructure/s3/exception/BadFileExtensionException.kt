package hs.kr.entrydsm.feed.infrastructure.s3.exception

import hs.kr.entrydsm.feed.global.error.exception.EquusException
import hs.kr.entrydsm.feed.global.error.exception.ErrorCode

object BadFileExtensionException : EquusException(
    ErrorCode.BAD_FILE_EXTENSION
)
