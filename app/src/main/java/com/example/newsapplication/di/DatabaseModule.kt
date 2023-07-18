package com.example.newsapplication.di

import android.content.Context
import androidx.room.Room
import com.example.newsapplication.db.NewsDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun providesNewsDB(@ApplicationContext context: Context):NewsDB{
        return Room.databaseBuilder(context,NewsDB::class.java,"NewsDB").build()
    }
}