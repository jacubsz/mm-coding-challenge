package com.github.jacubsz.mm.networking.plugin

import com.github.jacubsz.mm.contract.datasource.LessonsDataSource
import com.github.jacubsz.mm.contract.model.Lesson
import com.github.jacubsz.mm.networking.api.MMLessonsApi
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class AppNetworkingPlugin @Inject constructor(
    private val lessonsApi: MMLessonsApi
) : LessonsDataSource {

    override fun getLessons(): Single<List<Lesson>> = lessonsApi.getLessons()
        .map { wrapperDTO ->
            wrapperDTO.lessons.map { lessonDTO ->
                lessonDTO.toLesson()
            }
        }
}