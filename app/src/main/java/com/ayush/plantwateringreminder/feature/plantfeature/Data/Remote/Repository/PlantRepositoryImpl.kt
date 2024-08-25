package com.ayush.plantwateringreminder.feature.plantfeature.Data.Remote.Repository

import android.util.Log
import com.ayush.plantwateringreminder.feature.plantfeature.Data.Remote.Mapper.toDomain
import com.ayush.plantwateringreminder.feature.plantfeature.Data.Remote.PlantApi
import com.ayush.plantwateringreminder.feature.plantfeature.Domain.Model.Plant
import com.ayush.plantwateringreminder.feature.plantfeature.Domain.Model.PlantDetails
import com.ayush.plantwateringreminder.feature.plantfeature.Domain.Repository.PlantRepository
import com.ayush.plantwateringreminder.feature.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlantRepositoryImpl @Inject constructor(
    private val api: PlantApi
): PlantRepository {

    override suspend fun getPlantList(query:String): Flow<Resource<List<Plant>>>{
         return flow {
             try {
                 emit(Resource.Loading(true))
                 val response = api.getPlant(q = query)
                 if (response.isSuccessful) {
                     val data = response.body()?.data?.map { it.toDomain() } ?: emptyList()
                     emit(Resource.Success(data = data))
                 } else {
                     emit(Resource.Error("Can't fetch data."))
                 }
             }catch (e: IOException){
                 e.printStackTrace()
                 emit(Resource.Error(e.message ?: "Unknown error"))
             }catch (e: HttpException){
                 e.printStackTrace()
                 emit(Resource.Error(e.message ?: "Unknown error"))
             }
             catch (e: Exception) {
                 e.printStackTrace()
                 Log.e("PlantRepository", "Exception: ${e.message}")
                 emit(Resource.Error(e.message ?: "Unknown error"))
             }finally {
                 emit(Resource.Loading(false))
             }
         }
    }

    override suspend fun getPlantDetails(id: Int): Resource<PlantDetails>
    {
      return  try {
            val response = api.getPlantDetails(id)
            if (response.isSuccessful) {
                val data = response.body()?.toDomain()
                if (data != null) {
                    Resource.Success(
                        data = data
                    )
                } else {
                    Resource.Error("Can't fetch data 1")
                }
            } else {
                Resource.Error("Can't fetch the api")
            }
        } catch (e: IOException)
        {
            e.printStackTrace()
            Resource.Error(message ="Can't load the data 2 ")
        }catch (e:HttpException){
              e.printStackTrace()
              Resource.Error(message ="Can't load the data 3")
        }

    }
}