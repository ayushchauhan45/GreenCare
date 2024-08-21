package com.ayush.plantwateringreminder.feature.WaterRemindinngFeature.Domain.Repository

import com.ayush.plantwateringreminder.feature.WaterRemindinngFeature.Domain.Model.PlantEntity
import kotlinx.coroutines.flow.Flow

interface PlantWateringRepository {
    fun getPlant(): Flow<List<PlantEntity>>

    suspend fun insertPlant(plant: PlantEntity)

    suspend fun deletePlant(plant: PlantEntity)

    suspend fun getPlantById(id:Int): PlantEntity?
}