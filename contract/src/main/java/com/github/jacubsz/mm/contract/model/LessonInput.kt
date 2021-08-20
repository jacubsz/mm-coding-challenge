package com.github.jacubsz.mm.contract.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LessonInput(
    val startIndex: Int,
    val endIndex: Int
) : Parcelable