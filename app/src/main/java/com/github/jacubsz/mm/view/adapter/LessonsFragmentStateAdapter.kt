package com.github.jacubsz.mm.view.adapter

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.github.jacubsz.mm.contract.model.Lesson
import com.github.jacubsz.mm.view.LessonFragment
import com.github.jacubsz.mm.view.LessonsScrollingActivity

class LessonsFragmentStateAdapter(
    lessonsScrollingActivity: LessonsScrollingActivity
) : FragmentStateAdapter(lessonsScrollingActivity) {

    private val lessons: MutableList<Lesson> = mutableListOf()

    override fun getItemCount(): Int = lessons.size

    override fun createFragment(position: Int): Fragment =
        LessonFragment.newInstance(lessons[position])

    @SuppressLint("NotifyDataSetChanged")
    fun updateLessons(lessons: List<Lesson>) {
        this.lessons.clear()
        this.lessons.addAll(lessons)
        notifyDataSetChanged() // that is a shortcut, it can be improved with DiffUtils and notifyRange functions
    }

    fun verifyLessonCompletion(currentPosition: Int, lesson: Lesson) =
        lessons[currentPosition] == lesson

}