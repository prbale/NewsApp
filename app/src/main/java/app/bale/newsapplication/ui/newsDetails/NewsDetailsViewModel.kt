package app.bale.newsapplication.ui.newsDetails

import androidx.lifecycle.viewModelScope
import app.bale.newsapplication.data.local.ArticleDao
import app.bale.newsapplication.data.model.Articles
import app.bale.newsapplication.data.repository.NewsRepository
import app.bale.newsapplication.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * View Model for [[NewsDetailsFragment]]
 */
class NewsDetailsViewModel @Inject constructor(private val repository: NewsRepository) :
    BaseViewModel() {

    fun bookmarkArticle(article: Articles) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.bookmarkArticle(article)
        }
    }

}