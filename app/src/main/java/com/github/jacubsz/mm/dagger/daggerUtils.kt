package com.github.jacubsz.mm.dagger

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.github.jacubsz.mm.viewmodel.AppViewModel
import kotlin.reflect.KClass

fun <VM : AppViewModel> FragmentActivity.obtainViewModel(
    factory: Lazy<ViewModelProvider.Factory>,
    viewModelClass: KClass<VM>
) = lazy { ViewModelProvider(this, factory.value)[viewModelClass.java] }

fun <VM : AppViewModel> Fragment.obtainViewModel(
    factory: Lazy<ViewModelProvider.Factory>,
    viewModelClass: KClass<VM>
) = lazy { ViewModelProvider(this, factory.value)[viewModelClass.java] }