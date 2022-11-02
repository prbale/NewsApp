package app.bale.newsapplication.dependencyinjection.builder

import app.bale.newsapplication.presentation.bookmarked.BookmarkedFragment
import app.bale.newsapplication.presentation.newsDetails.NewsDetailsFragment
import app.bale.newsapplication.presentation.newsList.NewsFragment
import app.bale.newsapplication.presentation.newsList.NewsListModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/*
 * This builder provides android injector service to fragments
 */
@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector(modules = [NewsListModule::class])
    abstract fun bindNewsFragment(): NewsFragment

    @ContributesAndroidInjector
    abstract fun bindNewsDetailsFragment(): NewsDetailsFragment

    @ContributesAndroidInjector(modules = [NewsListModule::class])
    abstract fun bindBookmarkedFragment(): BookmarkedFragment

}