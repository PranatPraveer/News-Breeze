package com.example.newsapplication.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapplication.models.Article
import com.example.newsapplication.models.News
import com.example.newsapplication.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: NewsRepository) : ViewModel() {
    val NewsLiveData get() = repository.newsLiveData
    val statusLiveData get() = repository.statusLiveData
    val dbLiveData get() = repository.dbLiveData
    fun getNews(){
        viewModelScope.launch {
            repository.getNews()
        }
    }
    fun addBookMark(article: Article){
        viewModelScope.launch {
            repository.addBookmark(article)
        }
    }
    fun getBookMarks(){
        viewModelScope.launch {
            repository.getBookmarks()
        }
    }
    fun deleteBookMark(article: Article){
        viewModelScope.launch {
            repository.deleteBookmark(article)
        }
    }
}