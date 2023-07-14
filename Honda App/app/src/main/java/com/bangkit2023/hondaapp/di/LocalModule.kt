package com.bangkit2023.hondaapp.di

import android.app.Application
import androidx.room.Room
import com.bangkit2023.hondaapp.data.local.MotorcycleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    @Provides
    @Singleton
    fun provideDatabase(application: Application) = Room
        .databaseBuilder(application, MotorcycleDatabase::class.java, "hondaapp.db")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideDao(database: MotorcycleDatabase) = database.motorcycleDao()
}