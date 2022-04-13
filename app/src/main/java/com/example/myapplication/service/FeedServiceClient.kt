package com.example.myapplication.service

import com.example.myapplication.service.response.TopNewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface FeedServiceClient {

    @Headers("Content-Type: application/json")
    @GET("top-headlines")
    fun getTopNewsAsync(
        @Query("country") country: String, @Query("apiKey") apiKey: String
    ): Call<TopNewsResponse>

    @Headers("Content-Type: application/json")
    @GET("top-headlines")
    fun getTopNewsByCategoryAsync(
        @Query("category") category: String, @Query("apiKey") apiKey: String
    ): Call<TopNewsResponse>

    @Headers("Content-Type: application/json")
    @GET("top-headlines")
    fun searchNewsBySortAsync(
        @Query("q") qury: String, @Query("sortBy") sort: String, @Query("apiKey") apiKey: String
    ): Call<TopNewsResponse>

    @Headers("Content-Type: application/json")
    @GET("top-headlines")
    fun searchNewsAsync(
        @Query("q") qury: String, @Query("apiKey") apiKey: String
    ): Call<TopNewsResponse>
}