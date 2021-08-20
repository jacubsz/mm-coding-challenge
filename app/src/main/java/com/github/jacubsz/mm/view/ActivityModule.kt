package com.github.jacubsz.mm.view

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeAboutActivity(): AboutActivity

    @ContributesAndroidInjector
    abstract fun contributeLessonsScrollingActivity(): LessonsScrollingActivity

    @ContributesAndroidInjector
    abstract fun contributeLessonFragment(): LessonFragment

    @ContributesAndroidInjector
    abstract fun contributeLessonsCompletedActivity(): LessonsCompletedActivity

}