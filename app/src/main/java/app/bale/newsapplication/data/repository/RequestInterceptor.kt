package app.bale.newsapplication.data.repository

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

    class RequestInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest: Request = chain.request()
        val originalHttpUrl: HttpUrl = originalRequest.url
        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("country", "in")
            .addQueryParameter("apiKey", "b46cd64b5e7a412e983a289e36e5c430")
            .build() // Modify if needs to append any query param here
        val request: Request = originalRequest.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}