package com.ayush.plantwateringreminder.feature.PlantDatabase.Domain.Repository

import com.ayush.plantwateringreminder.feature.PlantDatabase.Domain.Model.PlantEntity
import com.ayush.plantwateringreminder.feature.plantfeature.Domain.Model.PlantDetails
import kotlinx.coroutines.flow.Flow

interface PlantWateringRepository {
    fun getPlant(): Flow<List<PlantEntity>>

    suspend fun insertPlant(plant: PlantDetails)

    suspend fun deletePlant(plant: PlantDetails)

    suspend fun getPlantById(id:Int): PlantEntity?
}