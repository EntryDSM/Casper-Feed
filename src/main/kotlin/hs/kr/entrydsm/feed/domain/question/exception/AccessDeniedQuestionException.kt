package hs.kr.entrydsm.feed.domain.question.exception

import hs.kr.entrydsm.feed.global.error.exception.EquusException
import hs.kr.entrydsm.feed.global.error.exception.ErrorCode

object AccessDeniedQuestionException : EquusException(
    ErrorCode.ACCESS_DENIED_QUESTION
)
