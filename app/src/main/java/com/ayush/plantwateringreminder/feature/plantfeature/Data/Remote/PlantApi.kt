package com.ayush.plantwateringreminder.feature.plantfeature.Data.Remote

import com.ayush.plantwateringreminder.feature.plantfeature.Data.Remote.Model.PlantDtoDetail
import com.ayush.plantwateringreminder.feature.plantfeature.Data.Remote.Model.PlantResponse
import com.ayush.plantwateringreminder.feature.plantfeature.util.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PlantApi {
    @GET("api/species-list")
    suspend fun getPlant(
        @Query("key") apiKey: String = API_KEY
    ): Response<PlantResponse>

    @GET("api/species/details/")
    suspend fun getPlantDetails(
        @Query("id") id:String,
        @Query("key") apiKey: String = API_KEY
    ): Response<PlantDtoDetail>
}