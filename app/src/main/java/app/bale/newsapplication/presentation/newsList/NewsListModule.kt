package app.bale.newsapplication.presentation.newsList

import dagger.Module
import dagger.Provides

@Module
class NewsListModule {

    @Provides
    internal fun provideNewsAdapter(): NewsAdapter = NewsAdapter()

}