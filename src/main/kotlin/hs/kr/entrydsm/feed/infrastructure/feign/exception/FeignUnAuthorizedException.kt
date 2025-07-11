package hs.kr.entrydsm.feed.infrastructure.feign.exception

import hs.kr.entrydsm.feed.global.error.exception.EquusException
import hs.kr.entrydsm.feed.global.error.exception.ErrorCode

object FeignUnAuthorizedException : EquusException(
    ErrorCode.FEIGN_UNAUTHORIZED
)
