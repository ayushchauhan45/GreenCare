package com.ayush.plantwateringreminder.feature.plantfeature.Domain.Repository

import com.ayush.plantwateringreminder.feature.plantfeature.Domain.Model.Plant
import com.ayush.plantwateringreminder.feature.plantfeature.Domain.Model.PlantDetails
import com.ayush.plantwateringreminder.feature.util.Resource
import kotlinx.coroutines.flow.Flow

interface PlantRepository {

    suspend fun getPlantList(
        query:String
    ):Flow<Resource<List<Plant>>>

    suspend fun getPlantDetails(id:Int): Resource<PlantDetails>

}