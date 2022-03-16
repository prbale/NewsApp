package app.bale.newsapplication.dependencyinjection.module.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.bale.newsapplication.ui.newsDetails.NewsDetailsViewModel
import app.bale.newsapplication.ui.newsList.NewsViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Add ViewModels here.
 */
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    internal abstract fun bindNewsViewModel(viewModel: NewsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NewsDetailsViewModel::class)
    internal abstract fun bindNewsDetailsViewModel(viewModel: NewsDetailsViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}