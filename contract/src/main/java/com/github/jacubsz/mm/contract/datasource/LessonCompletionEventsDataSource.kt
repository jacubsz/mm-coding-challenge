package com.github.jacubsz.mm.contract.datasource

import com.github.jacubsz.mm.contract.model.LessonCompletionEvent
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

interface LessonCompletionEventsDataSource {

    fun saveLessonCompletionEvent(lessonCompletionEvent: LessonCompletionEvent): Completable

    fun listenToLessonCompletionEvents(): Flowable<List<LessonCompletionEvent>>

}