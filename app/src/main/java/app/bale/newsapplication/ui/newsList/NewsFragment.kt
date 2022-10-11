package app.bale.newsapplication.ui.newsList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import app.bale.newsapplication.R
import app.bale.newsapplication.data.model.Article
import app.bale.newsapplication.data.model.NewsResponse
import app.bale.newsapplication.data.util.Resource
import app.bale.newsapplication.data.util.Status
import app.bale.newsapplication.databinding.FragmentNewsBinding
import app.bale.newsapplication.extension.gone
import app.bale.newsapplication.extension.showMessage
import app.bale.newsapplication.extension.visible
import app.bale.newsapplication.listeners.OnItemClickListener
import app.bale.newsapplication.ui.base.BaseFragment
import javax.inject.Inject

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener

class NewsFragment :
        BaseFragment<NewsViewModel, FragmentNewsBinding>(NewsViewModel::class.java) {

    @Inject
    internal lateinit var adapter: NewsAdapter

    override val layoutRes: Int
        get() = R.layout.fragment_news

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

        setPullToRefresh()

        navController = Navigation.findNavController(view)

        // Item click listener
        adapter.setOnItemClickListener(onNewsItemClickListener())

        // Observe
        viewModel.newsResponse.observe(viewLifecycleOwner) { state -> handleState(state) }

        // Trigger call
        viewModel.getAllTopHeadLines()
    }

    private fun setPullToRefresh() {
        dataBinding.swipeRefreshLayout.setOnRefreshListener(OnRefreshListener {
            dataBinding.swipeRefreshLayout.isRefreshing = false
            // Trigger call
            viewModel.getAllTopHeadLines()
        })
    }

    private fun handleState(state: Resource<NewsResponse>) {
        when (state.status) {
            Status.SUCCESS -> loadNews(state.data)
            Status.LOADING -> showLoading()
            Status.ERROR -> showError(state.message ?: "Something went wrong ¯\\_(ツ)_/¯")
        }
    }

    private fun onNewsItemClickListener() = object : OnItemClickListener {
        override fun onItemClick(item: Article?) {
            item?.let {
                val bundle = bundleOf("ARTICLE" to it)
                navController.navigate(
                    R.id.action_newsFragment_to_newsDetailsFragment,
                    bundle)
            }
        }
    }

    private fun showError(errorMessage: String) {
        dataBinding.loadingIndicator.gone()
        showMessage(errorMessage)
    }

    private fun showLoading() = dataBinding.loadingIndicator.visible()

    private fun loadNews(data: NewsResponse?) {
        dataBinding.loadingIndicator.gone()
        data?.let { adapter.setArticlesList(it.articles) }
    }
}