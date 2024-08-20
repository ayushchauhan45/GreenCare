package com.ayush.plantwateringreminder.feature.WaterRemindinngFeature.Data.Repository

import com.ayush.plantwateringreminder.feature.WaterRemindinngFeature.Domain.Model.PlantEntity
import com.ayush.plantwateringreminder.feature.WaterRemindinngFeature.Domain.Repository.PlantRepository
import kotlinx.coroutines.flow.Flow

class ClassRepositoryImpl():PlantRepository {
    override fun getPlant(): Flow<List<PlantEntity>> {
        TODO("Not yet implemented")
    }

    override fun insertPlant(plant: PlantEntity) {
        TODO("Not yet implemented")
    }

    override fun deletePlant(plant: PlantEntity) {
        TODO("Not yet implemented")
    }

    override fun getPlantById(id: Int): PlantEntity? {
        TODO("Not yet implemented")
    }
}