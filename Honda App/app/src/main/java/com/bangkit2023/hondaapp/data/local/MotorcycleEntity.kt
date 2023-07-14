package com.bangkit2023.hondaapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "motorcycle")
data class MotorcycleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String,
    val description: String,
    val harga: String,
    val photoUrl: String,
    val rating: Double,
    val totalReview: Int,
    var isFavorite: Boolean = false,
)
