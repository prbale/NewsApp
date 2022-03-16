package app.bale.newsapplication.ui.newsList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import app.bale.newsapplication.data.model.NewsResponse
import app.bale.newsapplication.data.repository.NewsRepository
import app.bale.newsapplication.data.util.Resource
import app.bale.newsapplication.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * View Model for [NewsFragment]
 */
class NewsViewModel @Inject constructor(private val repository: NewsRepository) : BaseViewModel() {

    val newsResponse = MutableLiveData<Resource<NewsResponse>>()

    fun getAllTopHeadLines() {

        viewModelScope.launch {

            newsResponse.value = Resource.loading(null)

            try {
                val data = repository.getAllTopHeadLines()
                newsResponse.value = Resource.success(data)
            }
            catch (error: Exception) {
                newsResponse.value = Resource.error(
                    error.message ?: "An error has occurred !",
                    null)
            }
        }
    }
}