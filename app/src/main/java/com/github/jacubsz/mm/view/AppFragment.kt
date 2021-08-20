package com.github.jacubsz.mm.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.github.jacubsz.mm.BR
import com.github.jacubsz.mm.dagger.obtainViewModel
import com.github.jacubsz.mm.viewmodel.AppViewModel
import dagger.android.support.DaggerFragment
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject
import kotlin.reflect.KClass

abstract class AppFragment<VDB : ViewDataBinding, VM : AppViewModel>(
    viewModelClass: KClass<VM>
) : DaggerFragment() {

    protected lateinit var viewBinding: VDB

    protected val disposables = CompositeDisposable()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected val viewModel by obtainViewModel(lazy { viewModelFactory }, viewModelClass)

    protected abstract fun bindView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): VDB

    protected abstract fun initView(savedInstanceState: Bundle?)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = bindView(inflater, container)
        viewBinding.setVariable(BR.viewModel, viewModel)
        initView(savedInstanceState)
        viewModel.init()
        return viewBinding.root
    }

    override fun onDestroy() {
        viewModel.destroy()
        disposables.clear()
        super.onDestroy()
    }
}