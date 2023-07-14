package com.bangkit2023.hondaapp.data.repository

import com.bangkit2023.hondaapp.data.local.MotorcycleDao
import com.bangkit2023.hondaapp.data.local.MotorcycleEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(private val motorcycleDao: MotorcycleDao) {
    fun getAllmotorcycle() = motorcycleDao.getAllmotorcycle()
    fun getAllFavoritemotorcycle() = motorcycleDao.getAllFavoritemotorcycle()
    fun getmotorcycle(id: Int) = motorcycleDao.getmotorcycle(id)
    fun searchmotorcycle(query: String) = motorcycleDao.searchmotorcycle(query)
    suspend fun insertAllmotorcycle(motorcycle: List<MotorcycleEntity>) = motorcycleDao.insertAllmotorcycle(motorcycle)
    suspend fun updateFavoritemotorcycle(id: Int, isFavorite: Boolean) = motorcycleDao.updateFavoritemotorcycle(id, isFavorite)
}