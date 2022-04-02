package app.bale.newsapplication

import android.app.Activity
import android.app.Application
import android.content.ContextWrapper
import androidx.fragment.app.Fragment
import app.bale.newsapplication.dependencyinjection.component.DaggerApplicationComponent
import com.pixplicity.easyprefs.library.Prefs
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


class NewsApplication: Application(), HasActivityInjector, HasSupportFragmentInjector {

    @Inject
    internal lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    internal lateinit var supportFragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = supportFragmentInjector

    companion object {
        lateinit var applicationInstance: NewsApplication
    }

    override fun onCreate() {
        super.onCreate()
        initializeComponent()

        initializePreference()
    }

    private fun initializePreference() {
        // Initialize the Prefs class
        Prefs.Builder()
            .setContext(this)
            .setMode(ContextWrapper.MODE_PRIVATE)
            .setPrefsName(packageName)
            .setUseDefaultSharedPreference(true)
            .build()
    }

    private fun initializeComponent() {
        DaggerApplicationComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }

}