package com.github.jacubsz.mm.networking.api

import com.github.jacubsz.mm.networking.api.model.LessonsWrapperDTO
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET


interface MMLessonsApi {

    companion object {
        private const val BASE_PATH = "/lessons"
    }

    @GET("$BASE_PATH/")
    fun getLessons(): Single<LessonsWrapperDTO>

}