package com.bangkit2023.hondaapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MotorcycleEntity::class], version = 1, exportSchema = false)
abstract class MotorcycleDatabase : RoomDatabase() {
    abstract fun motorcycleDao(): MotorcycleDao
}