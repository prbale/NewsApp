package app.bale.newsapplication.ui.newsList

import androidx.paging.PagingSource
import androidx.paging.PagingState
import app.bale.newsapplication.data.model.Article
import app.bale.newsapplication.data.repository.NewsRepository

private const val INITIAL_PAGE_KEY = 1

class NewsDataSource(
    private val newsService: NewsRepository
) : PagingSource<Int, Article>() {

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val nextPage: Int = params.key ?: INITIAL_PAGE_KEY
            val response = newsService.getAllTopHeadLines(nextPage)
            val responseData = mutableListOf<Article>()
            val data = response.body()?.articles ?: emptyList()
            responseData.addAll(data)

            val prevKey = if (nextPage == 1) null else nextPage - 1

            LoadResult.Page(
                data = responseData,
                prevKey = null,
                nextKey = nextPage.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}
