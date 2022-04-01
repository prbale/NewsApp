package app.bale.newsapplication.listeners

import app.bale.newsapplication.data.model.Article


interface OnItemClickListener {
    fun onItemClick(item: Article?)
}