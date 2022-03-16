package app.bale.newsapplication.listeners

import app.bale.newsapplication.data.model.Articles


interface OnItemClickListener {
    fun onItemClick(item: Articles?)
}