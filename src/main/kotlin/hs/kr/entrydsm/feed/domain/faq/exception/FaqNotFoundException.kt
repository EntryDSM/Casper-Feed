package hs.kr.entrydsm.feed.domain.faq.exception

import hs.kr.entrydsm.feed.global.error.exception.EquusException
import hs.kr.entrydsm.feed.global.error.exception.ErrorCode

object FaqNotFoundException : EquusException(
    ErrorCode.FAQ_NOT_FOUND
)
