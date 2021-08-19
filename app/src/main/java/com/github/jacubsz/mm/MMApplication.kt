package com.github.jacubsz.mm

import android.util.Log
import com.github.jacubsz.mm.dagger.AppModule
import com.github.jacubsz.mm.dagger.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.reactivex.rxjava3.plugins.RxJavaPlugins

class MMApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent
            .factory()
            .create(AppModule(applicationContext))
            .also { it.inject(this) }

    override fun onCreate() {
        super.onCreate()
        configureGlobalRxErrorHandler()
    }

    private fun configureGlobalRxErrorHandler() {
        RxJavaPlugins.setErrorHandler {
            Log.e("RX stream error occurred", it.localizedMessage, it)
        }
    }
}