package app.bale.newsapplication.ui

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import app.bale.newsapplication.R
import app.bale.newsapplication.databinding.ActivityMainBinding
import android.graphics.drawable.ColorDrawable


/**
 * Not extended with BaseActivity as we don't need ViewModel for this activity
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment?

        navController = navHostFragment!!.navController

        val appBarConfiguration = AppBarConfiguration(setOf(R.id.newsFragment, R.id.bookmarkedFragment))

        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.navigationView.setupWithNavController(navController)

        NavigationUI.setupWithNavController(binding.navigationView, navController)

    }

    // To enable back functionality on navigation bar
    override fun onSupportNavigateUp() = navController.navigateUp()

}