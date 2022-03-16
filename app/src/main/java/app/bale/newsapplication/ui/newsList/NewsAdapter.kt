package app.bale.newsapplication.ui.newsList

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import app.bale.newsapplication.data.model.Articles
import app.bale.newsapplication.databinding.ItemBinding
import app.bale.newsapplication.extension.loadImage
import app.bale.newsapplication.listeners.OnItemClickListener
import com.github.marlonlom.utilities.timeago.TimeAgo
import java.time.Instant

class NewsAdapter: RecyclerView.Adapter<MainViewHolder>() {

    private var news = mutableListOf<Articles>()

    private var listener: OnItemClickListener? = null

    fun setDealsList(deals: List<Articles>) {
        this.news = deals.toMutableList()
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
            newsDate.text = newsItem.publishedAt?.let { dateTimeAgo(it) }
            newsDescription.text = newsItem.description
            root.setOnClickListener {
                listener?.onItemClick(newsItem)
            }
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun dateTimeAgo(date: String): String {
        val instant = Instant.parse(date)
        val ms = instant.toEpochMilli()
        return TimeAgo.using(ms).replaceFirstChar { it.uppercaseChar() }
    }

    override fun getItemCount(): Int = news.size

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        listener = onItemClickListener
    }
}

class MainViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root)