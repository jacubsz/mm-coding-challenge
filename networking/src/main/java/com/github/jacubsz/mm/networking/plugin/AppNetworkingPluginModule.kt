package com.github.jacubsz.mm.networking.plugin

import com.github.jacubsz.mm.contract.datasource.LessonsDataSource
import com.github.jacubsz.mm.networking.AppNetworkingModule
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(includes = [AppNetworkingModule::class])
abstract class AppNetworkingPluginModule {

    @Singleton
    @Binds
    internal abstract fun bindAppNetworkingPlugin(appNetworkingPlugin: AppNetworkingPlugin): LessonsDataSource

}