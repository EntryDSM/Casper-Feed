package hs.kr.entrydsm.feed.domain.reply.exception

import hs.kr.entrydsm.feed.global.error.exception.EquusException
import hs.kr.entrydsm.feed.global.error.exception.ErrorCode

object ReplyExistsException : EquusException(
    ErrorCode.REPLY_EXISTS
)
