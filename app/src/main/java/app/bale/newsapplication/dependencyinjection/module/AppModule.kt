package app.bale.newsapplication.dependencyinjection.module

import android.app.Application
import app.bale.newsapplication.constants.ApiConstants
import app.bale.newsapplication.data.local.ArticleDao
import app.bale.newsapplication.data.local.ArticleDatabase
import app.bale.newsapplication.data.repository.RequestInterceptor
import app.bale.newsapplication.data.repository.RetrofitService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().apply {
        connectTimeout(ApiConstants.CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
        readTimeout(ApiConstants.READ_TIMEOUT, TimeUnit.MILLISECONDS)
        writeTimeout(ApiConstants.WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
        addInterceptor(RequestInterceptor())
        addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
    }.build()


    @Provides
    internal fun provideRetrofitService(okHttpClient: OkHttpClient): RetrofitService =
        Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(RetrofitService::class.java)

    @Provides
    internal fun provideArticleDao(application: Application): ArticleDao =
        ArticleDatabase.getDatabase(application).articleDao()

}