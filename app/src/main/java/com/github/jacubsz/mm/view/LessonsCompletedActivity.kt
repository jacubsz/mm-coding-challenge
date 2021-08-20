package com.github.jacubsz.mm.view

import android.content.Context
import android.content.Intent
import com.github.jacubsz.mm.R
import com.github.jacubsz.mm.databinding.ActivityLessonsCompletedBinding
import com.github.jacubsz.mm.viewmodel.EmptyViewModel

class LessonsCompletedActivity : AppActivity<ActivityLessonsCompletedBinding, EmptyViewModel>(
    R.layout.activity_lessons_completed,
    EmptyViewModel::class
) {

    companion object {

        fun newIntent(context: Context) = Intent(context, LessonsCompletedActivity::class.java)

    }
}