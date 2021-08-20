package com.github.jacubsz.mm.viewmodel

sealed class LessonItemType

data class LessonItemInput(val text: String, val color: String, var input: String) :
    LessonItemType()

data class LessonItemText(val text: String, val color: String) : LessonItemType()