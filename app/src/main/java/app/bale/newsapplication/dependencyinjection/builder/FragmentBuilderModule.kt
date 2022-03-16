package app.bale.newsapplication.dependencyinjection.builder

import app.bale.newsapplication.ui.newsDetails.NewsDetailsFragment
import app.bale.newsapplication.ui.newsList.NewsFragment
import app.bale.newsapplication.ui.newsList.NewsListModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/*
 * This builder provides android injector service to fragments
 */
@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector(modules = [NewsListModule::class])
    abstract fun bindDealsFragment(): NewsFragment

    @ContributesAndroidInjector
    abstract fun bindDealDetailsFragment(): NewsDetailsFragment

}