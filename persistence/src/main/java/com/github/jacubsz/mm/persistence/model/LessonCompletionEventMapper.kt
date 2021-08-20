package com.github.jacubsz.mm.persistence.model

import com.github.jacubsz.mm.contract.model.LessonCompletionEvent
import java.util.Date

internal fun LessonCompletionEvent.toDatabaseEntity() = LessonCompletionEventEntity(
    null,
    lessonId,
    lessonStartTime,
    lessonCompletionTime ?: Date()
)

internal fun LessonCompletionEventEntity.toContractModel() = LessonCompletionEvent(
    lessonId,
    lessonStarted,
    lessonCompleted
)