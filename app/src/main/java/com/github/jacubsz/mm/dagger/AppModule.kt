package com.github.jacubsz.mm.dagger

import android.content.Context
import com.github.jacubsz.mm.BuildConfig
import com.github.jacubsz.mm.networking.plugin.NetworkingHost
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(
    private val applicationContext: Context
) {

    @Singleton
    @Provides
    fun provideApplicationContext(): Context = applicationContext

    @Singleton
    @Provides
    @NetworkingHost
    fun provideNetworkingHost() = BuildConfig.NETWORKING_HOST

}