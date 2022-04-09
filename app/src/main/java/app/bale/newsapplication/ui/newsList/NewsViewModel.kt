package app.bale.newsapplication.ui.newsList

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import app.bale.newsapplication.data.model.Article
import app.bale.newsapplication.data.repository.NewsRepository
import app.bale.newsapplication.ui.base.BaseViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * View Model for [NewsFragment]
 */
class NewsViewModel @Inject constructor(private val repository: NewsRepository) : BaseViewModel() {

    val listData: Flow<PagingData<Article>> = Pager(
        config = PagingConfig(pageSize = 38),
        pagingSourceFactory = { NewsDataSource(repository) }
    ).flow.cachedIn(viewModelScope)
}