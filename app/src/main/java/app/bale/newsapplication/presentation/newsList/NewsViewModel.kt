package app.bale.newsapplication.presentation.newsList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import app.bale.newsapplication.data.model.Article
import app.bale.newsapplication.data.model.NewsResponse
import app.bale.newsapplication.data.util.Resource
import app.bale.newsapplication.domain.usecase.DeleteBookMarkedArticleUsecase
import app.bale.newsapplication.domain.usecase.GetAllBookMarkedArticlesUsecase
import app.bale.newsapplication.domain.usecase.TopHeadLinesUsecase
import app.bale.newsapplication.presentation.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * View Model for [NewsFragment]
 */
class NewsViewModel @Inject constructor(
    private val topHeadLinesUsecase: TopHeadLinesUsecase,
    private val getAllBookMarkedArticlesUsecase: GetAllBookMarkedArticlesUsecase,
    private val deleteBookMarkedArticleUsecase: DeleteBookMarkedArticleUsecase
) : BaseViewModel() {

    val newsResponse = MutableLiveData<Resource<NewsResponse>>()
    val bookmarkedResponse = MutableLiveData<Resource<List<Article>>>()

    fun getAllTopHeadLines() {

        viewModelScope.launch {

            newsResponse.value = Resource.loading(null)

            try {
                val data = topHeadLinesUsecase.getAllTopHeadLines()
                newsResponse.value = Resource.success(data)
            }
            catch (error: Exception) {
                newsResponse.value = Resource.error(
                    error.message ?: "An error has occurred !",
                    null)
            }
        }
    }

    fun getAllBookmarkedArticles() {

        viewModelScope.launch(Dispatchers.IO) {

            bookmarkedResponse.postValue( Resource.loading(null) )

            try {
                val data = getAllBookMarkedArticlesUsecase.getAllBookMarkedArticlesUsecase()

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
            deleteBookMarkedArticleUsecase.deleteBookMarkedArticle(article)
        }
    }

}