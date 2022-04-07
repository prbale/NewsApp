package app.bale.newsapplication.ui.newsList

import androidx.recyclerview.widget.DiffUtil
import app.bale.newsapplication.data.model.Article

object DiffUtilCallback : DiffUtil.ItemCallback<Article>() {

    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.description == newItem.description
    }
}