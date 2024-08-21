package com.ayush.plantwateringreminder.feature.WaterRemindinngFeature.Data.Repository

import com.ayush.plantwateringreminder.feature.WaterRemindinngFeature.Data.DataSource.PlantDao
import com.ayush.plantwateringreminder.feature.WaterRemindinngFeature.Domain.Model.PlantEntity
import com.ayush.plantwateringreminder.feature.WaterRemindinngFeature.Domain.Repository.PlantWateringRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PlantWateringRepositoryImpl @Inject constructor(
    private val dao:PlantDao
) : PlantWateringRepository {
    override fun getPlant(): Flow<List<PlantEntity>> {
        return dao.getAllPlants()
    }

    override suspend fun insertPlant(plant: PlantEntity) {
        return dao.insertPlant(plant)
    }

    override suspend fun deletePlant(plant: PlantEntity) {
        return dao.deletePlant(plant)
    }

    override suspend fun getPlantById(id: Int): PlantEntity? {
        return dao.getPlantById(id)
    }
}