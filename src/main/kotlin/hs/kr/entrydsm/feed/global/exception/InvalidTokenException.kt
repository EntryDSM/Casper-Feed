package hs.kr.entrydsm.feed.global.exception

import hs.kr.entrydsm.feed.global.error.exception.EquusException
import hs.kr.entrydsm.feed.global.error.exception.ErrorCode

object InvalidTokenException : EquusException(
    ErrorCode.INVALID_TOKEN
)
