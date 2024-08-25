package com.ayush.plantwateringreminder.feature.PlantDatabase.Domain.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Plant")
data class PlantEntity(
    @PrimaryKey
    val id:Int,
    val commonName: String,
    val description: String,
    val thumbnailImageUrl: String,
    val originalImageUrl:String,
    val watering: String,
    val wateringUnit: String,
    val wateringValue: String,
    val wateringPeriod: String?
)
