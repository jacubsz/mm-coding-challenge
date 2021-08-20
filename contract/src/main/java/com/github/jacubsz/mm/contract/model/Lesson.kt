package com.github.jacubsz.mm.contract.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Lesson(
    val id: Int,
    val content: List<LessonContent>,
    val input: LessonInput?
) : Parcelable