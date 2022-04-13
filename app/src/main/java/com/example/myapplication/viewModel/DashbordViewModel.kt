package com.example.myapplication.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.BuildConfig
import com.example.myapplication.service.FeedService
import com.example.myapplication.service.response.TopNewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashBordViewModel : ViewModel() {

    var topNewsResponse = MutableLiveData<TopNewsResponse?>()
    var headlineResponse = MutableLiveData<TopNewsResponse?>()

    var inProgress = MutableLiveData<Boolean>()
    var error = MutableLiveData<String>()

    fun getTopNewsList() {
        inProgress.postValue(true)
        var result = FeedService.client.getTopNewsAsync("us", BuildConfig.NEWSFEED_KEY)
        result.enqueue(object : Callback<TopNewsResponse>{
            override fun onResponse(
                call: Call<TopNewsResponse>,
                response: Response<TopNewsResponse>
            ) {
                inProgress.postValue(false)
                if (response.body() != null) {
                    topNewsResponse.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<TopNewsResponse>, t: Throwable) {
                inProgress.postValue(false)
                error.postValue(t.message)
            }
        })

    }

    fun getHeadLineList(type : String) {
        inProgress.postValue(true)
        var result = FeedService.client.getTopNewsByCategoryAsync(type, BuildConfig.NEWSFEED_KEY)
        result.enqueue(object : Callback<TopNewsResponse>{
            override fun onResponse(
                call: Call<TopNewsResponse>,
                response: Response<TopNewsResponse>
            ) {
                inProgress.postValue(false)
                if (response.body() != null) {
                    headlineResponse.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<TopNewsResponse>, t: Throwable) {
                inProgress.postValue(false)
                error.postValue(t.message)
            }
        })
    }

    fun getHeadLineListBySearch(qury : String) {
        inProgress.postValue(true)
        var result = FeedService.client.searchNewsAsync(qury, BuildConfig.NEWSFEED_KEY)
        result.enqueue(object : Callback<TopNewsResponse>{
            override fun onResponse(
                call: Call<TopNewsResponse>,
                response: Response<TopNewsResponse>
            ) {
                inProgress.postValue(false)
                if (response.body() != null) {
                    headlineResponse.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<TopNewsResponse>, t: Throwable) {
                inProgress.postValue(false)
                error.postValue(t.message)
            }
        })
    }

    fun getHeadLineListBySearch(qury : String, sortBy : String) {
        inProgress.postValue(true)
        var result = FeedService.client.searchNewsBySortAsync(qury,sortBy, BuildConfig.NEWSFEED_KEY)
        result.enqueue(object : Callback<TopNewsResponse>{
            override fun onResponse(
                call: Call<TopNewsResponse>,
                response: Response<TopNewsResponse>
            ) {
                inProgress.postValue(false)
                if (response.body() != null) {
                    headlineResponse.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<TopNewsResponse>, t: Throwable) {
                inProgress.postValue(false)
                error.postValue(t.message)
            }
        })
    }

}