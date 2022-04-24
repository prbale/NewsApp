package app.bale.newsapplication.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

/**
 * Base class for all activities.
 */
abstract class BaseActivity<D: ViewDataBinding>
    : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @get:LayoutRes
    protected abstract val layoutRes: Int

    val binding by lazy {
        DataBindingUtil.setContentView(this, layoutRes) as D
    }

    /**
     * If you want to inject Dependency Injection
     * on your activity, you can override this.
     */
    open fun onInject() {}

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        onInject()
    }
}