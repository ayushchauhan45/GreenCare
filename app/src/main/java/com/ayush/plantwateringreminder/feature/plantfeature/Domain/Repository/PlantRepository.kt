package com.ayush.plantwateringreminder.feature.plantfeature.Domain.Repository

import com.ayush.plantwateringreminder.feature.plantfeature.Domain.Model.Plant
import com.ayush.plantwateringreminder.feature.plantfeature.Domain.Model.PlantDetails
import com.ayush.plantwateringreminder.feature.plantfeature.util.Resource

interface PlantRepository {

    suspend fun getPlantList():Resource<List<Plant>>

    suspend fun getPlantDetails(id:String):Resource<PlantDetails>

}