package app.bale.newsapplication.ui.newsList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import app.bale.newsapplication.R
import app.bale.newsapplication.data.model.Article
import app.bale.newsapplication.databinding.FragmentNewsBinding
import app.bale.newsapplication.listeners.OnItemClickListener
import app.bale.newsapplication.ui.base.BaseFragment
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class NewsFragment : BaseFragment<NewsViewModel, FragmentNewsBinding>(NewsViewModel::class.java) {

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
            it.setHasFixedSize(true)
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
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.listData.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }

    private fun onNewsItemClickListener() = object : OnItemClickListener {
        override fun onItemClick(item: Article?) {
            item?.let {
                val bundle = bundleOf("ARTICLE" to it)
                navController.navigate(
                    R.id.action_newsFragment_to_newsDetailsFragment,
                    bundle
                )
            }
        }
    }
}
