package com.github.jacubsz.mm.view

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import com.github.jacubsz.mm.contract.model.Lesson
import com.github.jacubsz.mm.databinding.FragmentLessonBinding
import com.github.jacubsz.mm.rxutils.Thread
import com.github.jacubsz.mm.rxutils.dispatch
import com.github.jacubsz.mm.view.LessonsScrollingActivity.Companion.LESSON_COMPLETION_FRAGMENT_RESULT
import com.github.jacubsz.mm.view.LessonsScrollingActivity.Companion.LESSON_COMPLETION_FRAGMENT_RESULT_DATA
import com.github.jacubsz.mm.viewmodel.LessonItemInput
import com.github.jacubsz.mm.viewmodel.LessonItemText
import com.github.jacubsz.mm.viewmodel.LessonViewModel
import io.reactivex.rxjava3.kotlin.addTo

class LessonFragment : AppFragment<FragmentLessonBinding, LessonViewModel>(LessonViewModel::class) {

    companion object {

        private const val ARG_LESSON = "ARG_LESSON"

        fun newInstance(lesson: Lesson): LessonFragment =
            LessonFragment().apply {
                arguments = Bundle().also { it.putParcelable(ARG_LESSON, lesson) }
            }

        private fun getLesson(arguments: Bundle?) =
            arguments?.getParcelable<Lesson>(ARG_LESSON)

    }

    override fun bindView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLessonBinding = FragmentLessonBinding.inflate(inflater, container, false)

    override fun initView(savedInstanceState: Bundle?) {
        getLesson(arguments)?.let { lesson ->
            viewModel.setLesson(lesson)
            viewBinding.nextButton.setOnClickListener {
                viewModel.saveLessonCompletion()
                parentFragmentManager.setFragmentResult(LESSON_COMPLETION_FRAGMENT_RESULT, bundleOf(LESSON_COMPLETION_FRAGMENT_RESULT_DATA to lesson))
            }
        }

        viewModel.lessonItems
            .dispatch(Thread.IO, Thread.MAIN)
            .map { lessonItems ->
                lessonItems.map { lessonItem ->
                    when (lessonItem) {
                        is LessonItemInput -> EditText(requireContext()).also {
                            it.setTextColor(Color.parseColor(lessonItem.color))
                            it.addTextChangedListener { inputText ->
                                if (inputText.toString() == lessonItem.text) {
                                    viewModel.setSuccess(lessonItem)
                                } else {
                                    viewModel.setFailure(lessonItem)
                                }
                            }
                        }
                        is LessonItemText -> TextView(requireContext()).also {
                            it.setTextColor(Color.parseColor(lessonItem.color))
                            it.text = lessonItem.text
                        }
                    }
                }
            }
            .subscribe { views ->
                views.forEach {
                    viewBinding.itemsLinearLayout.addView(it)
                }
            }
            .addTo(disposables)
    }
}