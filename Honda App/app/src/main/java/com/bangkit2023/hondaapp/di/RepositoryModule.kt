package com.bangkit2023.hondaapp.di

import com.bangkit2023.hondaapp.data.local.MotorcycleDao
import com.bangkit2023.hondaapp.data.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    @ViewModelScoped
    fun provideRepository(motorcycleDao: MotorcycleDao) = Repository(motorcycleDao)
}