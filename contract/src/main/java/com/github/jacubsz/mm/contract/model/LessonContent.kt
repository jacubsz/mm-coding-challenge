package com.github.jacubsz.mm.contract.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LessonContent(
    val color: String,
    val text: String
) : Parcelable