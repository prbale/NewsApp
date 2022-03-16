package app.bale.newsapplication.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import app.bale.newsapplication.R
import app.bale.newsapplication.databinding.ActivityMainBinding
import app.bale.newsapplication.extension.addFragment
import app.bale.newsapplication.extension.replaceFragment
import app.bale.newsapplication.ui.newsList.NewsFragment

/**
 * Not extended with BaseActivity as we don't need ViewModel for this activity
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Fragment Handling
        setCurrentFragment(NewsFragment())
    }

    private fun setCurrentFragment(fragment: Fragment) =
        addFragment(fragment, R.id.nav_host_fragment_activity_main)
}