package com.github.jacubsz.mm.persistence.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.github.jacubsz.mm.persistence.model.DateConverter
import com.github.jacubsz.mm.persistence.model.LessonCompletionEventEntity
import com.github.jacubsz.mm.persistence.model.LessonCompletionEventsDao

const val DATABASE_NAME = "lesson-completion-events-database"

@Database(
    version = 1,
    entities = [LessonCompletionEventEntity::class]
)
@TypeConverters(DateConverter::class)
internal abstract class LessonCompletionEventsDatabase : RoomDatabase() {

    abstract fun lessonCompletionEventsDao(): LessonCompletionEventsDao

}
