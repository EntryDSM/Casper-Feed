package hs.kr.entrydsm.feed.global.exception

import hs.kr.entrydsm.feed.global.error.exception.EquusException
import hs.kr.entrydsm.feed.global.error.exception.ErrorCode

object InternalServerErrorException : EquusException(
    ErrorCode.INTERNAL_SERVER_ERROR
)
