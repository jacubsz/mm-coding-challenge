package com.github.jacubsz.mm.view

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeAboutActivity(): AboutActivity

}