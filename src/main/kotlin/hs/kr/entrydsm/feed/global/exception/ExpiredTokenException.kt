package hs.kr.entrydsm.feed.global.exception

import hs.kr.entrydsm.feed.global.error.exception.EquusException
import hs.kr.entrydsm.feed.global.error.exception.ErrorCode

object ExpiredTokenException : EquusException(
    ErrorCode.EXPIRED_TOKEN
)
