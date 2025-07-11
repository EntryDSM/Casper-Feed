package hs.kr.entrydsm.feed.infrastructure.feign.exception

import hs.kr.entrydsm.feed.global.error.exception.EquusException
import hs.kr.entrydsm.feed.global.error.exception.ErrorCode

object FeignServerError : EquusException(
    ErrorCode.FEIGN_SERVER_ERROR
)
