package app.bale.newsapplication.ui.bookmarked

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.bale.newsapplication.R
import app.bale.newsapplication.constants.AppConstants
import app.bale.newsapplication.data.model.Article
import app.bale.newsapplication.data.util.Resource
import app.bale.newsapplication.data.util.Status
import app.bale.newsapplication.databinding.FragmentBookmarkedBinding
import app.bale.newsapplication.extension.gone
import app.bale.newsapplication.extension.md5Hash
import app.bale.newsapplication.extension.showMessage
import app.bale.newsapplication.extension.visible
import app.bale.newsapplication.listeners.OnItemClickListener
import app.bale.newsapplication.ui.base.BaseFragment
import app.bale.newsapplication.ui.newsList.NewsAdapter
import app.bale.newsapplication.ui.newsList.NewsViewModel
import com.pixplicity.easyprefs.library.Prefs
import javax.inject.Inject

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

        deleteSwipeHandling()

        return dataBinding.root
    }

    private fun deleteSwipeHandling() {
        val swipeHandler = object : SwipeToDeleteCallback(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = dataBinding.rvMain.adapter as NewsAdapter
                adapter.removeAt(viewHolder.adapterPosition) { article ->
                    deleteBookMark(article)
                }
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(dataBinding.rvMain)
    }

    private fun deleteBookMark(article: Article) {
        viewModel.deleteBookmarkedArticle(article)
        deleteFromPreference(article)
    }

    private fun deleteFromPreference(article: Article) {

        val list: Set<String> = Prefs.getOrderedStringSet(AppConstants.BOOKMARKED_PREF_KEY, setOf())

        val hashSet = hashSetOf<String>()

        list.forEach { e -> hashSet.add(e) }

        article.title?.md5Hash()?.let { hashSet.remove(it) }

        Prefs.putOrderedStringSet(AppConstants.BOOKMARKED_PREF_KEY, hashSet)

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

    private fun handleState(state: Resource<List<Article>>) {
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

    private fun loadNews(data: List<Article>?) {
        dataBinding.loadingIndicator.gone()
        data?.let { adapter.setArticlesList(it) }
    }
}