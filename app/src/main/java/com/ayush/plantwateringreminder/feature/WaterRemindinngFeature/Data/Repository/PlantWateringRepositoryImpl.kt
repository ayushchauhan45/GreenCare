package com.ayush.plantwateringreminder.feature.WaterRemindinngFeature.Data.Repository

import com.ayush.plantwateringreminder.feature.WaterRemindinngFeature.Data.DataSource.PlantDao
import com.ayush.plantwateringreminder.feature.WaterRemindinngFeature.Domain.Model.PlantEntity
import com.ayush.plantwateringreminder.feature.WaterRemindinngFeature.Domain.Repository.PlantWateringRepository
import com.ayush.plantwateringreminder.feature.plantfeature.Data.Remote.Mapper.toDomain
import com.ayush.plantwateringreminder.feature.plantfeature.Data.Remote.Mapper.toEntity
import com.ayush.plantwateringreminder.feature.plantfeature.Data.Remote.Model.PlantDtoDetail
import com.ayush.plantwateringreminder.feature.plantfeature.Domain.Model.PlantDetails
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PlantWateringRepositoryImpl @Inject constructor(
    private val dao:PlantDao
) : PlantWateringRepository {
    override fun getPlant(): Flow<List<PlantEntity>> {
        return dao.getAllPlants()
    }

    override suspend fun insertPlant(plant: PlantDetails) {
        return dao.insertPlant(plant.toEntity())
    }

    override suspend fun deletePlant(plant: PlantDetails) {
        return dao.deletePlant(plant.toEntity())
    }

    override suspend fun getPlantById(id: Int): PlantEntity? {
        return dao.getPlantById(id)
    }
}