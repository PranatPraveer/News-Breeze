package com.example.newsapplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newsapplication.api.NewsAPI
import com.example.newsapplication.db.NewsDB
import com.example.newsapplication.models.Article
import com.example.newsapplication.models.News
import com.example.newsapplication.utlis.NetworkResult
import org.json.JSONObject

import javax.inject.Inject

class NewsRepository @Inject constructor(private val newsAPI: NewsAPI,private  val newsDB: NewsDB) {
    private val _newsLiveData=MutableLiveData<NetworkResult<News>>()
    val newsLiveData:LiveData<NetworkResult<News>>
        get() = _newsLiveData

    val _statusLiveData = MutableLiveData<NetworkResult<String>>()
    val statusLiveData:LiveData<NetworkResult<String>>
        get() = _statusLiveData

    val _dbLiveData=MutableLiveData<List<Article>>()
    val dbLiveData:LiveData<List<Article>>
        get() = _dbLiveData

    suspend fun getNews(){
        _newsLiveData.postValue(NetworkResult.Loading())
        val response= newsAPI.getNews("techcrunch","08dd0f1e64f24ddbae9364b6e172b532")
        if (response.body()!=null && response.isSuccessful){
            //newsDB.getNewsDao().createNews(response.body()!!.articles)
            _newsLiveData.postValue(NetworkResult.Success(response.body()!!))
        }
        else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _newsLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
        } else {
            _newsLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
        }

   }

    suspend fun getBookmarks(){
        val response=newsDB.getNewsDao().getBookmarks()
        _dbLiveData.postValue(response)
    }
    suspend fun addBookmark(article: Article){
        newsDB.getNewsDao().createNews(article)
    }
    suspend fun deleteBookmark(article: Article){
        newsDB.getNewsDao().deleteBookmarks(article)
        val response=newsDB.getNewsDao().getBookmarks()
        _dbLiveData.postValue(response)
    }
}