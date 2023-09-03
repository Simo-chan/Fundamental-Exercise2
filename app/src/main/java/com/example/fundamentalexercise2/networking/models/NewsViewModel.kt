package com.example.fundamentalexercise2.networking.models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fundamentalexercise2.networking.DTOtoNewsItemMapper
import com.example.fundamentalexercise2.networking.RetrofitInstance
import com.example.fundamentalexercise2.recyclerview.NewsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException


class NewsViewModel : ViewModel() {
    private val TAG = "NewsViewModel"
    private val _newsLiveData = MutableLiveData<List<NewsItem>>()
    val newsLiveData: LiveData<List<NewsItem>> = _newsLiveData

    fun loadNews() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d(TAG, "Loading news in ${Thread.currentThread().name}")

            val response = try {
                RetrofitInstance.api.getNews()
            } catch (e: IOException) {
                Log.e(TAG, "IOException occurred,check internet connection maybe")
                return@launch
            } catch (e: HttpException) {
                Log.e(TAG, "Unexpected response")
                return@launch
            }
            if (response.isSuccessful && response.body() != null) {

                Log.d(TAG, "Mapping response data in ${Thread.currentThread().name}")
                val topStoriesResponse = response.body()!!
                val newsData = DTOtoNewsItemMapper.map(topStoriesResponse.results)

                withContext(Dispatchers.Main) {
                    _newsLiveData.value = newsData
                    Log.d(TAG, "Live data updated in ${Thread.currentThread().name}")
                }
            }
        }
    }
}