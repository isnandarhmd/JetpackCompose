package com.bangkit2023.hondaapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MotorcycleDao {
    @Query("SELECT * FROM motorcycle")
    fun getAllmotorcycle(): Flow<List<MotorcycleEntity>>

    @Query("SELECT * FROM motorcycle WHERE isFavorite = 1")
    fun getAllFavoritemotorcycle(): Flow<List<MotorcycleEntity>>

    @Query("SELECT * FROM motorcycle WHERE id = :id")
    fun getmotorcycle(id: Int): Flow<MotorcycleEntity>

    @Query("SELECT * FROM motorcycle WHERE name LIKE '%' || :query || '%'")
    fun searchmotorcycle(query: String): Flow<List<MotorcycleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllmotorcycle(motorcycleList: List<MotorcycleEntity>)

    @Query("UPDATE motorcycle SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun updateFavoritemotorcycle(id: Int, isFavorite: Boolean)
}