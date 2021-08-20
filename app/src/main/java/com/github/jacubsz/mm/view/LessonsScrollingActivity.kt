package com.github.jacubsz.mm.view

import android.os.Bundle
import com.github.jacubsz.mm.R
import com.github.jacubsz.mm.contract.model.Lesson
import com.github.jacubsz.mm.databinding.ActivityLessonsScrollingBinding
import com.github.jacubsz.mm.rxutils.Thread
import com.github.jacubsz.mm.rxutils.dispatch
import com.github.jacubsz.mm.view.adapter.LessonsFragmentStateAdapter
import com.github.jacubsz.mm.viewmodel.LessonsScrollingViewModel
import com.google.android.material.snackbar.Snackbar
import io.reactivex.rxjava3.kotlin.addTo

class LessonsScrollingActivity :
    AppActivity<ActivityLessonsScrollingBinding, LessonsScrollingViewModel>(
        R.layout.activity_lessons_scrolling,
        LessonsScrollingViewModel::class
    ) {

    companion object {
        const val LESSON_COMPLETION_FRAGMENT_RESULT = "LESSON_COMPLETION_FRAGMENT_RESULT"
        const val LESSON_COMPLETION_FRAGMENT_RESULT_DATA = "LESSON_COMPLETION_FRAGMENT_RESULT_DATA"
    }

    private val lessonsAdapter = LessonsFragmentStateAdapter(this)

    override fun initView(savedInstanceState: Bundle?) {
        viewBinding.lessonsViewPager.adapter = lessonsAdapter
        viewBinding.lessonsViewPager.isUserInputEnabled = false
        viewBinding.aboutText.setOnClickListener { startActivity(AboutActivity.newIntent(this)) }
        subscribeToLessons()
        subscribeToErrorMessage()

        supportFragmentManager.setFragmentResultListener(
            LESSON_COMPLETION_FRAGMENT_RESULT,
            this
        ) { requestKey, bundle ->
            if (requestKey == LESSON_COMPLETION_FRAGMENT_RESULT) {
                bundle.getParcelable<Lesson>(LESSON_COMPLETION_FRAGMENT_RESULT_DATA)
                    ?.let { completedLesson ->
                        trySwitchingToNextLessonOrShowCompletionActivity(completedLesson)
                    }
            }
        }
    }

    private fun subscribeToLessons() {
        viewModel.lessons
            .dispatch(Thread.IO, Thread.MAIN)
            .subscribe(lessonsAdapter::updateLessons)
            .addTo(disposables)
    }

    private fun subscribeToErrorMessage() {
        viewModel.errorMessageResource
            .dispatch(Thread.IO, Thread.MAIN)
            .filter { it > 0 }
            .subscribe { Snackbar.make(viewBinding.root, it, Snackbar.LENGTH_LONG).show() }
            .addTo(disposables)
    }

    private fun trySwitchingToNextLessonOrShowCompletionActivity(completedLesson: Lesson) {
        val currentItemPosition = viewBinding.lessonsViewPager.currentItem
        if (lessonsAdapter.verifyLessonCompletion(currentItemPosition, completedLesson)) {
            if (currentItemPosition + 1 < lessonsAdapter.itemCount) {
                viewBinding.lessonsViewPager.setCurrentItem(currentItemPosition + 1, true)
            } else {
                startActivity(LessonsCompletedActivity.newIntent(this))
                finish()
            }
        }
    }
}