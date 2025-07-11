package hs.kr.entrydsm.feed.domain.screen.exception

import hs.kr.entrydsm.feed.global.error.exception.EquusException
import hs.kr.entrydsm.feed.global.error.exception.ErrorCode

object ScreenNotFoundException : EquusException(
    ErrorCode.SCREEN_NOT_FOUND
)
