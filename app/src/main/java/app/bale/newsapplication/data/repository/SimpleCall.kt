package app.bale.newsapplication.data.repository


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SimpleCall<T>(call: Call<T>) : Call<T> by call {

    private var onSuccess: (Response<T>) -> Unit = { Unit }
    private var onError: (Throwable) -> Unit = { Unit }

    fun doOnSuccess(onSuccess: (Response<T>) -> Unit): SimpleCall<T> {
        this.onSuccess = onSuccess
        return this
    }

    fun doOnError(onError: (Throwable) -> Unit): SimpleCall<T> {
        this.onError = onError
        return this
    }

    fun run() {
        enqueue(object : Callback<T> {
            override fun onFailure(call: Call<T>, throwable: Throwable) = onError(throwable)

            override fun onResponse(call: Call<T>, response: Response<T>) = onSuccess(response)
        })
    }
}

fun <T> Call<T>.toSimpleCall(): SimpleCall<T> {
    return SimpleCall(this)
}