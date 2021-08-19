package com.github.jacubsz.mm.contract.model

import java.util.Date

data class LessonCompletionEvent(
    val lessonId: Int,
    val lessonStartTime: Date,
    val lessonCompletionTime: Date?
)