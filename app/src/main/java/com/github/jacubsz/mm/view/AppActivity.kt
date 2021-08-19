package com.github.jacubsz.mm.view

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.github.jacubsz.mm.BR
import com.github.jacubsz.mm.dagger.obtainViewModel
import com.github.jacubsz.mm.viewmodel.AppViewModel
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject
import kotlin.reflect.KClass

abstract class AppActivity<T : ViewDataBinding, VM : AppViewModel>(
    @LayoutRes private val layoutResource: Int,
    viewModelClass: KClass<VM>
) : DaggerAppCompatActivity() {

    protected lateinit var viewBinding: T

    protected val disposables = CompositeDisposable()

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    protected val viewModel by obtainViewModel(lazy { viewModelFactory }, viewModelClass)

    protected abstract fun initView(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, layoutResource)
        viewBinding.setVariable(BR.viewModel, viewModel)
        viewModel.init()
        initView(savedInstanceState)
    }

    override fun onDestroy() {
        viewModel.destroy()
        super.onDestroy()
    }
}