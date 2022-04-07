package app.bale.newsapplication.ui.bookmarked

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import app.bale.newsapplication.data.model.Article
import app.bale.newsapplication.data.model.NewsResponse
import app.bale.newsapplication.data.repository.NewsRepository
import app.bale.newsapplication.data.util.Resource
import app.bale.newsapplication.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * View Model for [NewsFragment]
 */
class BookmarkedNewsViewModel @Inject constructor(private val repository: NewsRepository) : BaseViewModel() {

    val newsResponse = MutableLiveData<Resource<NewsResponse>>()
    val bookmarkedResponse = MutableLiveData<Resource<List<Article>>>()

    fun getAllBookmarkedArticles() {

        viewModelScope.launch(Dispatchers.IO) {

            bookmarkedResponse.postValue( Resource.loading(null) )

            try {
                val data = repository.getAllBookmarkedArticles()

                bookmarkedResponse.postValue( Resource.success(data) )
            }
            catch (error: Exception) {
                bookmarkedResponse.postValue( Resource.error(
                    error.message ?: "An error has occurred !",
                    null) )
            }
        }
    }

    fun deleteBookmarkedArticle(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteBookmarkedArticle(article)
        }
    }

}