package com.github.jacubsz.mm.networking.api.model

import com.github.jacubsz.mm.contract.model.LessonContent

data class LessonContentDTO(
    val color: String,
    val text: String
) {

    fun toContent() = LessonContent(color, text)

}