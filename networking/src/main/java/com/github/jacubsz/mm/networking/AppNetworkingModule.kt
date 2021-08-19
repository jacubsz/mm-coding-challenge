package com.github.jacubsz.mm.networking

import com.github.jacubsz.mm.networking.api.MMLessonsApi
import com.github.jacubsz.mm.networking.plugin.NetworkingHost
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppNetworkingModule {

    @Singleton
    @Provides
    fun provideRetrofit(@NetworkingHost host: String): Retrofit =
        Retrofit.Builder()
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(host)
            .build()

    @Singleton
    @Provides
    fun provideMMLessonsApi(retrofit: Retrofit): MMLessonsApi =
        retrofit.create(MMLessonsApi::class.java)

}