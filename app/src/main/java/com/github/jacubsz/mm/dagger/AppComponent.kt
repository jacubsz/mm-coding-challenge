package com.github.jacubsz.mm.dagger

import com.github.jacubsz.mm.MMApplication
import com.github.jacubsz.mm.networking.plugin.AppNetworkingPluginModule
import com.github.jacubsz.mm.persistence.plugin.AppPersistencePluginModule
import com.github.jacubsz.mm.view.ActivityModule
import com.github.jacubsz.mm.viewmodel.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,

        AppModule::class,

        ActivityModule::class,
        ViewModelModule::class,

        AppNetworkingPluginModule::class,
        AppPersistencePluginModule::class
    ]
)
interface AppComponent : AndroidInjector<MMApplication> {

    @Component.Factory
    interface Factory {
        fun create(
            appModule: AppModule
        ): AppComponent
    }
}