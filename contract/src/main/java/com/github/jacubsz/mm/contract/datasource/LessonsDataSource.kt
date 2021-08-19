package com.github.jacubsz.mm.contract.datasource

import com.github.jacubsz.mm.contract.model.Lesson
import io.reactivex.rxjava3.core.Single

interface LessonsDataSource {

    fun getLessons(): Single<List<Lesson>>

}