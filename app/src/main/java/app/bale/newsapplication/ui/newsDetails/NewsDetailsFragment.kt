package app.bale.newsapplication.ui.newsDetails

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import app.bale.newsapplication.R
import app.bale.newsapplication.data.model.Articles
import app.bale.newsapplication.databinding.FragmentNewsDetailsBinding
import app.bale.newsapplication.dependencyinjection.module.viewmodel.ViewModelFactory
import app.bale.newsapplication.extension.*
import app.bale.newsapplication.ui.base.BaseFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class NewsDetailsFragment : BaseFragment<NewsDetailsViewModel, FragmentNewsDetailsBinding>(NewsDetailsViewModel::class.java)  {

    lateinit var newsDetailsViewModel: NewsDetailsViewModel

    lateinit var article: Articles

    private var binding: FragmentNewsDetailsBinding? = null

    override val layoutRes: Int
        get() = R.layout.fragment_news_details

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        article = requireArguments().getParcelable<Articles>("ARTICLE") as Articles
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        newsDetailsViewModel = ViewModelProvider(this, viewModelFactory)[NewsDetailsViewModel::class.java]

        binding = FragmentNewsDetailsBinding.inflate(inflater)
        val root: View = binding!!.root

        displayNewsDetails(article)

        return root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun displayNewsDetails(articles: Articles) {
        binding?.let { binding ->

            binding.newsTitle.text = articles.title
            binding.newsImage.loadImage(articles.urlToImage)
            binding.newsDescription.text = articles.content.appendMore()
            binding.newsSource.text = articles.source?.name ?: ""
            binding.newsDate.text = articles.publishedAt?.convertTime(requireContext())
            binding.readFullNewsBtn.setOnClickListener {
                articles.url?.let { it1 -> context?.launchWebsite(it1) }
            }
            binding.bookmarkNews.setOnClickListener { bookmarkArticle(articles) }
            binding.shareNews.setOnClickListener {
                articles.url?.let { data ->
                    context?.shareContent(data)
                }
            }
        }
    }

    private fun bookmarkArticle(articles: Articles) {
        viewModel.bookmarkArticle(articles)
        showMessage("Bookmarked !!")
    }
}