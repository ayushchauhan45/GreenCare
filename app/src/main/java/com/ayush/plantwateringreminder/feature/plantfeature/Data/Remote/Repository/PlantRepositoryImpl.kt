package com.ayush.plantwateringreminder.feature.plantfeature.Data.Remote.Repository

import com.ayush.plantwateringreminder.feature.plantfeature.Data.Remote.Mapper.toDomain
import com.ayush.plantwateringreminder.feature.plantfeature.Data.Remote.PlantApi
import com.ayush.plantwateringreminder.feature.plantfeature.Domain.Model.Plant
import com.ayush.plantwateringreminder.feature.plantfeature.Domain.Model.PlantDetails
import com.ayush.plantwateringreminder.feature.plantfeature.Domain.Repository.PlantRepository
import com.ayush.plantwateringreminder.feature.plantfeature.util.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlantRepositoryImpl @Inject constructor(
    private val api: PlantApi
): PlantRepository {

    override suspend fun getPlantList(): Resource<List<Plant>> {
         return try {
             val response = api.getPlant()
             if (response.isSuccessful) {
                 val data  = response.body()?.data?.toDomain()?: emptyList()
                 Resource.Success(data = data)
             }else{
                 Resource.Error("Can't fetch data.")
             }

         }catch (e:Exception){
             e.printStackTrace()
             Resource.Error(e.message?:"Unknown error")
         }
    }

    override suspend fun getPlantDetails(id: String): Resource<PlantDetails> {
        return try {
            val response = api.getPlantDetails(id)
            if (response.isSuccessful){
               val data =  response.body()?.toDomain()
                if(data!= null){
                    Resource.Success(
                        data = data
                    )
                }
                else{
                    Resource.Error("Can't fetch data")
                }
            }else{
                Resource.Error("Can't fetch the api")
            }
        }catch (e:Exception){
            e.printStackTrace()
            Resource.Error(e.message?:"Unknown error")
        }
    }
}