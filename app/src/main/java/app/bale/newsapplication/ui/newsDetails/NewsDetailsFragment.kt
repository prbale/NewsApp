package app.bale.newsapplication.ui.newsDetails

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import app.bale.newsapplication.R
import app.bale.newsapplication.constants.AppConstants
import app.bale.newsapplication.data.model.Article
import app.bale.newsapplication.databinding.FragmentNewsDetailsBinding
import app.bale.newsapplication.extension.*
import app.bale.newsapplication.ui.base.BaseFragment
import com.pixplicity.easyprefs.library.Prefs
import dagger.android.support.AndroidSupportInjection


class NewsDetailsFragment : BaseFragment<NewsDetailsViewModel, FragmentNewsDetailsBinding>(NewsDetailsViewModel::class.java)  {

    lateinit var newsDetailsViewModel: NewsDetailsViewModel

    lateinit var article: Article

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
        article = requireArguments().getParcelable<Article>("ARTICLE") as Article
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
    private fun displayNewsDetails(article: Article) {
        binding?.let { binding ->

            binding.newsTitle.text = article.title
            binding.newsImage.loadImage(article.urlToImage)
            binding.newsDescription.text = article.content.appendMore()
            binding.newsSource.text = article.source?.name ?: ""
            binding.newsDate.text = article.publishedAt?.convertTime(requireContext())
            binding.readFullNewsBtn.setOnClickListener {
                article.url?.let { it1 -> context?.launchWebsite(it1) }
            }
            binding.bookmarkNews.setOnClickListener {
                article.bookmarkArticle(
                    onAdd = {
                        viewModel.bookmarkArticle(article)
                        binding.bookmarkNews.setBackgroundResource(R.drawable.ic_bookmark_filled)
                        showMessage("Bookmarked Added !!")
                    },
                    onRemove = {
                        viewModel.removeBookmarkArticle(article)
                        binding.bookmarkNews.setBackgroundResource(R.drawable.ic_bookmark)
                        showMessage("Bookmarked Removed !!")
                    }
                )
            }
            binding.shareNews.setOnClickListener {
                article.url?.let { data ->
                    context?.shareContent(data)
                }
            }
        }

        // Check whether article is bookmarked.
        if(article.checkBookmark()) {
            binding?.bookmarkNews?.setBackgroundResource(R.drawable.ic_bookmark_filled)
        } else {
            binding?.bookmarkNews?.setBackgroundResource(R.drawable.ic_bookmark)
        }

    }
}
