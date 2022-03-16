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
import app.bale.newsapplication.extension.covertTimeToText
import app.bale.newsapplication.extension.dateTimeAgo
import app.bale.newsapplication.extension.launchWebsite
import app.bale.newsapplication.extension.loadImage
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class NewsDetailsFragment : Fragment() {

    lateinit var dealDetailsViewModel: NewsDetailsViewModel

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
        activity?.actionBar?.title = "News Application"
        activity?.title = "News Application"
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                activity?.supportFragmentManager?.popBackStack()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
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

        val news: Articles = arguments?.getParcelable<Articles>("ARTICLE") as Articles
        displayDealDetails(news)

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
        }
    }

    companion object {
        fun createInstance(articles: Articles): NewsDetailsFragment {
            val fragment = NewsDetailsFragment()
            val bundle = Bundle().apply {
                putParcelable("ARTICLE", articles)
            }
            fragment.arguments = bundle
            return fragment
        }
    }

}