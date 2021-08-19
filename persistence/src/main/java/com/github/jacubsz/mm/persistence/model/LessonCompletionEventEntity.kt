package com.github.jacubsz.mm.persistence.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
internal data class LessonCompletionEventEntity(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name="lesson_id") val lessonId: Int,
    @ColumnInfo(name = "lesson_started") val lessonStarted: Date,
    @ColumnInfo(name = "lesson_completed") val lessonCompleted: Date
)