package com.github.jacubsz.mm.viewmodel

import androidx.databinding.ObservableBoolean
import com.github.jacubsz.mm.contract.datasource.LessonCompletionEventsDataSource
import com.github.jacubsz.mm.contract.model.Lesson
import com.github.jacubsz.mm.contract.model.LessonCompletionEvent
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import java.util.Date
import javax.inject.Inject

class LessonViewModel @Inject constructor(
    private val lessonCompletionEventsDataSource: LessonCompletionEventsDataSource
) : AppViewModel() {

    private val lessonItemsSubject = BehaviorSubject.create<List<LessonItemType>>()
    val lessonItems: Flowable<List<LessonItemType>> = lessonItemsSubject.toFlowable(BackpressureStrategy.LATEST)

    val nextButtonEnabled = ObservableBoolean(false)

    private val successfulLessonItems = mutableListOf<LessonItemInput>()
    private var expectedSuccessfulLessonItemsCount = -1
    private var lessonStartTime: Date? = null
    private var lessonId: Int? = null

    fun setLesson(lesson: Lesson) {
        lessonId = lesson.id
        val lessonItems = divideLessonsToInputsAndTexts(lesson)
        expectedSuccessfulLessonItemsCount = lessonItems.count { it is LessonItemInput }
        if (expectedSuccessfulLessonItemsCount == 0) {
            nextButtonEnabled.set(true)
        }
        lessonItemsSubject.onNext(lessonItems)
        lessonStartTime = Date()
    }

    fun setSuccess(lessonItem: LessonItemInput) {
        if (!successfulLessonItems.contains(lessonItem)) {
            successfulLessonItems.add(lessonItem)
        }
        if (expectedSuccessfulLessonItemsCount > 0 && expectedSuccessfulLessonItemsCount == successfulLessonItems.size) {
            nextButtonEnabled.set(true)
        }
    }

    fun setFailure(lessonItem: LessonItemInput) {
        if (successfulLessonItems.contains(lessonItem)) {
            successfulLessonItems.remove(lessonItem)
        }
        nextButtonEnabled.set(false)
    }

    fun saveLessonCompletion() {
        lessonId?.let { lessonId ->
            lessonCompletionEventsDataSource.saveLessonCompletionEvent(
                LessonCompletionEvent(
                    lessonId,
                    lessonStartTime ?: Date(),
                    Date()
                )
            )
        }
    }

    private fun divideLessonsToInputsAndTexts(lesson: Lesson): List<LessonItemType> {
        val lessonItems = mutableListOf<LessonItemType>()
        lesson.input?.let { lessonInput ->
            var contentIndexCounter = 0
            lesson.content.forEach { lessonContent ->
                if (contentIndexCounter > lessonInput.endIndex || contentIndexCounter + lessonContent.text.length <= lessonInput.startIndex) {
                    lessonItems.add(LessonItemText(lessonContent.text, lessonContent.color))
                } else {
                    if (lessonInput.startIndex > contentIndexCounter) {
                        if (lessonInput.startIndex - contentIndexCounter > 0) {
                            lessonItems.add(
                                LessonItemText(
                                    lessonContent.text.substring(
                                        0,
                                        lessonInput.startIndex - contentIndexCounter
                                    ),
                                    lessonContent.color
                                )
                            )
                        }
                        if (lessonInput.endIndex > contentIndexCounter + lessonContent.text.length) {
                            lessonItems.add(
                                LessonItemInput(
                                    lessonContent.text.substring(lessonInput.startIndex - contentIndexCounter),
                                    lessonContent.color,
                                    ""
                                )
                            )
                        } else {
                            lessonItems.add(
                                LessonItemInput(
                                    lessonContent.text.substring(
                                        lessonInput.startIndex - contentIndexCounter,
                                        lessonInput.endIndex - contentIndexCounter + 1
                                    ), lessonContent.color, ""
                                )
                            )
                            lessonItems.add(
                                LessonItemText(
                                    lessonContent.text.substring(
                                        lessonInput.endIndex - contentIndexCounter + 1
                                    ), lessonContent.color
                                )
                            )
                        }
                    } else {
                        if (lessonInput.endIndex > contentIndexCounter + lessonContent.text.length) {
                            lessonItems.add(
                                LessonItemInput(
                                    lessonContent.text,
                                    lessonContent.color,
                                    ""
                                )
                            )
                        } else {
                            lessonItems.add(
                                LessonItemInput(
                                    lessonContent.text.substring(
                                        0,
                                        lessonInput.endIndex - contentIndexCounter + 1
                                    ), lessonContent.color, ""
                                )
                            )
                            lessonItems.add(
                                LessonItemText(
                                    lessonContent.text.substring(
                                        lessonInput.endIndex - contentIndexCounter + 1
                                    ), lessonContent.color
                                )
                            )
                        }
                    }
                }
                contentIndexCounter += lessonContent.text.length
            }
        } ?: lesson.content.forEach { lessonContent ->
            lessonItems.add(LessonItemText(lessonContent.text, lessonContent.color))
        }
        return lessonItems
    }
}