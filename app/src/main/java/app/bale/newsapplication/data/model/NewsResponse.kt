package app.bale.newsapplication.data.model

data class NewsResponse (
 var status : String? = null,
 var totalResults : Int? = null,
 var articles : ArrayList<Article> = arrayListOf()
)