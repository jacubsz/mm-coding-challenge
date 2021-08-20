package com.github.jacubsz.mm.persistence.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao
internal interface LessonCompletionEventsDao {

    @Insert
    fun insertAll(vararg items: LessonCompletionEventEntity): Completable

    @Query("SELECT * FROM lessoncompletionevententity")
    fun getAll(): Flowable<List<LessonCompletionEventEntity>>

}