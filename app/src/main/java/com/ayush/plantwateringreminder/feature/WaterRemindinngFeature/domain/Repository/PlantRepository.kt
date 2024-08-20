package com.ayush.plantwateringreminder.feature.WaterRemindinngFeature.Domain.Repository

import com.ayush.plantwateringreminder.feature.WaterRemindinngFeature.Domain.Model.PlantEntity
import kotlinx.coroutines.flow.Flow

interface PlantRepository {
    fun getPlant(): Flow<List<PlantEntity>>

    fun insertPlant(plant: PlantEntity)

    fun deletePlant(plant: PlantEntity)

    fun getPlantById(id:Int): PlantEntity?
}