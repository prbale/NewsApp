package app.bale.newsapplication.ui.bookmarked

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import app.bale.newsapplication.R
import app.bale.newsapplication.data.model.Articles
import app.bale.newsapplication.data.model.NewsResponse
import app.bale.newsapplication.data.util.Resource
import app.bale.newsapplication.data.util.Status
import app.bale.newsapplication.databinding.FragmentBookmarkedBinding
import app.bale.newsapplication.databinding.FragmentNewsBinding
import app.bale.newsapplication.extension.gone
import app.bale.newsapplication.extension.showMessage
import app.bale.newsapplication.extension.visible
import app.bale.newsapplication.listeners.OnItemClickListener
import app.bale.newsapplication.ui.base.BaseFragment
import app.bale.newsapplication.ui.newsList.NewsAdapter
import app.bale.newsapplication.ui.newsList.NewsViewModel
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Use the [BookmarkedFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BookmarkedFragment:
    BaseFragment<NewsViewModel, FragmentBookmarkedBinding>(NewsViewModel::class.java) {

    @Inject
    internal lateinit var adapter: NewsAdapter

    override val layoutRes: Int
        get() = R.layout.fragment_bookmarked

    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        dataBinding.rvMain.also {
            it.adapter = adapter
            it.layoutManager = LinearLayoutManager(requireContext())
        }

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup(view)
    }

    private fun setup(view: View) {

        navController = Navigation.findNavController(view)

        // Item click listener
        adapter.setOnItemClickListener(onNewsItemClickListener())

        // Observe
        viewModel.bookmarkedResponse.observe(viewLifecycleOwner) { state -> handleState(state) }

        // Trigger call
        viewModel.getAllBookmarkedArticles()
    }

    private fun handleState(state: Resource<List<Articles>>) {
        when (state.status) {
            Status.SUCCESS -> loadNews(state.data)
            Status.LOADING -> showLoading()
            Status.ERROR -> showError(state.message ?: "Something went wrong ¯\\_(ツ)_/¯")
        }
    }

    private fun onNewsItemClickListener() = object : OnItemClickListener {
        override fun onItemClick(item: Articles?) {
            item?.let {
                val bundle = bundleOf("ARTICLE" to it)
                navController.navigate(
                    R.id.action_bookmarkedFragment_to_newsDetailsFragment,
                    bundle)
            }
        }
    }

    private fun showError(errorMessage: String) {
        dataBinding.loadingIndicator.gone()
        showMessage(errorMessage)
    }

    private fun showLoading() = dataBinding.loadingIndicator.visible()

    private fun loadNews(data: List<Articles>?) {
        dataBinding.loadingIndicator.gone()
        data?.let { adapter.setArticlesList(it) }
    }
}