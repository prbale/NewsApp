package app.bale.newsapplication.ui.newsList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.bale.newsapplication.data.model.Articles
import app.bale.newsapplication.databinding.ItemBinding
import app.bale.newsapplication.listeners.OnItemClickListener

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

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        val newsItem = news[position]

        holder.binding.apply {
            newsTitle.text = newsItem.title

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