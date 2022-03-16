package app.bale.newsapplication.ui.newsList

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import app.bale.newsapplication.data.model.Articles
import app.bale.newsapplication.databinding.ItemBinding
import app.bale.newsapplication.extension.dateTimeAgo
import app.bale.newsapplication.extension.loadImage
import app.bale.newsapplication.listeners.OnItemClickListener
import com.github.marlonlom.utilities.timeago.TimeAgo
import java.time.Instant

class NewsAdapter: RecyclerView.Adapter<MainViewHolder>() {

    private var news = mutableListOf<Articles>()

    private var listener: OnItemClickListener? = null

    fun setArticlesList(articles: List<Articles>) {
        this.news = articles.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        val newsItem = news[position]

        holder.binding.apply {

            newsImage.loadImage(newsItem.urlToImage)
            newsTitle.text = newsItem.title
            newsDate.text = newsItem.publishedAt?.dateTimeAgo()
            newsDescription.text = newsItem.description
            root.setOnClickListener {
                listener?.onItemClick(newsItem)
            }
        }

    }

    override fun getItemCount(): Int = news.size

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        listener = onItemClickListener
    }
}

class MainViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root)