package app.bale.newsapplication.ui.newsDetails

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import app.bale.newsapplication.data.model.Articles
import app.bale.newsapplication.databinding.FragmentNewsDetailsBinding
import app.bale.newsapplication.dependencyinjection.module.viewmodel.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject
import android.R

import android.content.Intent
import app.bale.newsapplication.extension.*
import app.bale.newsapplication.ui.base.BaseActivity


class NewsDetailsFragment : Fragment() {

    lateinit var dealDetailsViewModel: NewsDetailsViewModel

    lateinit var article: Articles

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var binding: FragmentNewsDetailsBinding? = null

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

        dealDetailsViewModel = ViewModelProvider(this, viewModelFactory)[NewsDetailsViewModel::class.java]

        binding = FragmentNewsDetailsBinding.inflate(inflater)
        val root: View = binding!!.root

        displayDealDetails(article)

        return root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun displayDealDetails(articles: Articles) {
        binding?.let { binding ->

            binding.newsTitle.text = articles.title
            binding.newsImage.loadImage(articles.urlToImage)
            binding.newsDescription.text = articles.content
            binding.newsDate.text = articles.publishedAt?.dateTimeAgo()
            binding.readFullNewsBtn.setOnClickListener {
                articles.url?.let { it1 -> context?.launchWebsite(it1) }
            }
            binding.shareNews.setOnClickListener {
                articles.url?.let { data ->
                    context?.shareContent(data)
                }
            }
        }
    }
}