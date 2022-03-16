package app.bale.newsapplication.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import app.bale.newsapplication.R
import app.bale.newsapplication.databinding.ActivityMainBinding
import app.bale.newsapplication.extension.addFragment
import app.bale.newsapplication.extension.replaceFragment
import app.bale.newsapplication.ui.newsList.NewsFragment
import androidx.navigation.NavController

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController


/**
 * Not extended with BaseActivity as we don't need ViewModel for this activity
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment?
        val navController = navHostFragment!!.navController
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.newsFragment, R.id.categoryFragment, R.id.bookmarkedFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navigationView.setupWithNavController(navController)
        NavigationUI.setupWithNavController(binding.navigationView, navController)

    }
}