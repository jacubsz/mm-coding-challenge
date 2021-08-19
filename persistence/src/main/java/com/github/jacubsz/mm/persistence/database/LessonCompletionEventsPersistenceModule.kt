package com.github.jacubsz.mm.persistence.database

import android.content.Context
import androidx.room.Room
import com.github.jacubsz.mm.persistence.model.LessonCompletionEventsDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class LessonCompletionEventsPersistenceModule {

    @Singleton
    @Provides
    fun provideLessonCompletionEventsDatabase(applicationContext: Context): LessonCompletionEventsDatabase =
        Room
            .databaseBuilder(
                applicationContext,
                LessonCompletionEventsDatabase::class.java,
                DATABASE_NAME
            )
            .build()

    @Singleton
    @Provides
    fun provideLessonCompletionEventsDao(database: LessonCompletionEventsDatabase): LessonCompletionEventsDao =
        database.lessonCompletionEventsDao()

}