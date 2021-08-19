package com.github.jacubsz.mm.persistence.plugin

import com.github.jacubsz.mm.contract.datasource.LessonCompletionEventsDataSource
import com.github.jacubsz.mm.contract.model.LessonCompletionEvent
import com.github.jacubsz.mm.persistence.model.LessonCompletionEventsDao
import com.github.jacubsz.mm.persistence.model.toDatabaseEntity
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

internal class AppPersistencePlugin @Inject internal constructor(
    private val lessonCompletionEventsDao: LessonCompletionEventsDao
) : LessonCompletionEventsDataSource {

    override fun saveLessonCompletionEvent(lessonCompletionEvent: LessonCompletionEvent): Completable =
        lessonCompletionEventsDao.insertAll(lessonCompletionEvent.toDatabaseEntity())

}