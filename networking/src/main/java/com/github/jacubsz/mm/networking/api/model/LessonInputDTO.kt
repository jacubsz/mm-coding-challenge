package com.github.jacubsz.mm.networking.api.model

import com.github.jacubsz.mm.contract.model.LessonInput

data class LessonInputDTO(
    val startIndex: Int,
    val endIndex: Int
) {

    fun toInput() = LessonInput(startIndex, endIndex)

}