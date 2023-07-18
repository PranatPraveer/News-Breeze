package com.example.newsapplication.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsapplication.models.Converters
import com.example.newsapplication.models.Article

@Database(entities = [Article::class], version = 1)
@TypeConverters(Converters::class)
abstract class NewsDB:RoomDatabase() {
    abstract fun getNewsDao():NewsDao
}