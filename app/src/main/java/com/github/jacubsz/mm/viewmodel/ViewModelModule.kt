package com.github.jacubsz.mm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.jacubsz.mm.dagger.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(EmptyViewModel::class)
    abstract fun bindEmptyViewModel(viewModel: EmptyViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LessonsScrollingViewModel::class)
    abstract fun bindLessonsScrollingViewModel(viewModel: LessonsScrollingViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LessonViewModel::class)
    abstract fun bindLessonViewModel(viewModel: LessonViewModel): ViewModel

}