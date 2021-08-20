package com.github.jacubsz.mm.viewmodel

import android.util.Log
import com.github.jacubsz.mm.R
import com.github.jacubsz.mm.contract.datasource.LessonCompletionEventsDataSource
import com.github.jacubsz.mm.contract.datasource.LessonsDataSource
import com.github.jacubsz.mm.contract.model.Lesson
import com.github.jacubsz.mm.contract.model.LessonCompletionEvent
import com.github.jacubsz.mm.rxutils.Thread
import com.github.jacubsz.mm.rxutils.dispatch
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.subjects.BehaviorSubject
import javax.inject.Inject

class LessonsScrollingViewModel @Inject constructor(
    private val lessonsDataSource: LessonsDataSource
): AppViewModel() {

    private val lessonsSubject = BehaviorSubject.create<List<Lesson>>()
    private val errorSubject = BehaviorSubject.create<Int>()

    val errorMessageResource: Observable<Int> = errorSubject.hide()
    val lessons: Flowable<List<Lesson>> = lessonsSubject.toFlowable(BackpressureStrategy.LATEST)

    override fun init() {
        loadLessons()
    }

    private fun loadLessons() {
        lessonsDataSource.getLessons()
            .dispatch(Thread.IO, Thread.IO)
            .doOnSuccess { errorSubject.onNext(0) }
            .subscribe(lessonsSubject::onNext, ::handleLessonsError)
            .addTo(disposables)
    }

    private fun handleLessonsError(throwable: Throwable) {
        Log.e("Network error", throwable.toString())
        errorSubject.onNext(R.string.lessons_loading_error)
    }
}