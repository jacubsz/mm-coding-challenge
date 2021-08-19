package com.github.jacubsz.mm.contract.model

data class Lesson(
    val id: Int,
    val content: List<LessonContent>,
    val checked: LessonInput
)