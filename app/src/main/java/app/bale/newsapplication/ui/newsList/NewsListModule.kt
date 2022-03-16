package app.bale.newsapplication.ui.newsList

import app.bale.newsapplication.ui.newsList.NewsAdapter
import dagger.Module
import dagger.Provides

@Module
class NewsListModule {

    @Provides
    internal fun provideDealsAdapter(): NewsAdapter = NewsAdapter()

}