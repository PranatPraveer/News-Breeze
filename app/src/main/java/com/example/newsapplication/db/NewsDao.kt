package com.example.newsapplication.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapplication.models.Article

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createNews(article: Article)

    @Query("SELECT * FROM Article")
    suspend fun getBookmarks():List<Article>

    @Delete
    suspend fun deleteBookmarks(article: Article)
}