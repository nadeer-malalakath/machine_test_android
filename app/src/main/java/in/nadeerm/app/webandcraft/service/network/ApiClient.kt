package `in`.nadeerm.app.webandcraft.service.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import org.koin.core.KoinComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory


object ApiClient : KoinComponent {



    private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val okHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.MINUTES)
            .readTimeout(20, TimeUnit.MINUTES)
            .writeTimeout(20, TimeUnit.MINUTES)
            .addInterceptor(logger)
            .addInterceptor{ chain ->
                val original = chain.request()
                val originalHttpUrl = original.url()
                val url = originalHttpUrl.newBuilder().build()
                val requestBuilder =
                    original.newBuilder()
                        .method(original.method(), original.body())
                        .url(url)


                val request = requestBuilder.build()
                chain.proceed(request)
            }.build()


    val instance : ApiService get() {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://run.mocky.io")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

        return retrofit.create(ApiService::class.java)
    }
}