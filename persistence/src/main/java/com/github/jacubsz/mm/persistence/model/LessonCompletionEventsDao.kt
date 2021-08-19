package com.github.jacubsz.mm.persistence.model

import androidx.room.Dao
import androidx.room.Insert
import io.reactivex.rxjava3.core.Completable

@Dao
internal interface LessonCompletionEventsDao {

    @Insert
    fun insertAll(vararg items: LessonCompletionEventEntity): Completable

}