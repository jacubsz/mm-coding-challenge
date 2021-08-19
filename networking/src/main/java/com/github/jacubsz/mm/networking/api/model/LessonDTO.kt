package com.github.jacubsz.mm.networking.api.model

import com.github.jacubsz.mm.contract.model.Lesson

internal data class LessonDTO(
    val id: Int,
    val content: List<LessonContentDTO>,
    val input: LessonInputDTO
) {

    fun toLesson(): Lesson = Lesson(id, content.map { it.toContent() }, input.toInput())
    
}