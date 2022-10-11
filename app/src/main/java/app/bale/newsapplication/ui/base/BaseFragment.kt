package app.bale.newsapplication.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


abstract class BaseFragment<V : ViewModel, D : ViewDataBinding>(
    private val mViewModelClass: Class<V>) : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected lateinit var viewModel: V
    protected lateinit var dataBinding: D

    open fun init() {}

    @get:LayoutRes
    protected abstract val layoutRes: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory)[mViewModelClass]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        return dataBinding.root
    }
}