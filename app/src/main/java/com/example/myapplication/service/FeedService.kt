package com.example.myapplication.service

import com.example.myapplication.BuildConfig
import com.example.myapplication.service.helpers.NetworkConnectionInterceptor
import com.example.myapplication.service.helpers.SkipSerializedStrategy
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object FeedService {

    private val interceptor = Interceptor { chain ->
        val url = chain.request()
            .url
            .newBuilder()
            .build()
        val request = chain.request()
            .newBuilder()
            .url(url)
            .build()
        chain.proceed(request)
    }

    private val apiClient = OkHttpClient().newBuilder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .addInterceptor(NetworkConnectionInterceptor())
        .build()

    private val gson =
        GsonBuilder().addSerializationExclusionStrategy(SkipSerializedStrategy.getStrategy())
            .create()
    private val gsonConverter = GsonConverterFactory.create(gson)

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder().client(apiClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(gsonConverter)
            .build()
    }

    val client: FeedServiceClient = getRetrofit().create(FeedServiceClient::class.java)

}