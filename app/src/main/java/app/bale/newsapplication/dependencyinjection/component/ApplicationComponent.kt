package app.bale.newsapplication.dependencyinjection.component

import android.app.Application
import app.bale.newsapplication.NewsApplication
import app.bale.newsapplication.dependencyinjection.builder.ActivityBuilderModule
import app.bale.newsapplication.dependencyinjection.builder.FragmentBuilderModule
import app.bale.newsapplication.dependencyinjection.module.AppModule
import app.bale.newsapplication.dependencyinjection.module.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = [
    AndroidSupportInjectionModule::class,
    AndroidInjectionModule::class,
    AppModule::class,
    ActivityBuilderModule::class,
    FragmentBuilderModule::class,
    ViewModelModule::class
])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: NewsApplication)
}