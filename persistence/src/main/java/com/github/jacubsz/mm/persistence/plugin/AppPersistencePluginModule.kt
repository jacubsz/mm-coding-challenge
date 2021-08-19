package com.github.jacubsz.mm.persistence.plugin

import com.github.jacubsz.mm.contract.datasource.LessonCompletionEventsDataSource
import com.github.jacubsz.mm.persistence.database.LessonCompletionEventsPersistenceModule
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(includes = [LessonCompletionEventsPersistenceModule::class])
abstract class AppPersistencePluginModule {

    @Singleton
    @Binds
    internal abstract fun bindAppPersistencePlugin(appPersistencePlugin: AppPersistencePlugin): LessonCompletionEventsDataSource

}