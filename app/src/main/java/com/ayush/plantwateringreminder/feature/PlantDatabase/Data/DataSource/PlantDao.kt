package com.ayush.plantwateringreminder.feature.PlantDatabase.Data.DataSource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ayush.plantwateringreminder.feature.PlantDatabase.Domain.Model.PlantEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlantDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPlant(plant: PlantEntity)

    @Query("Select * from Plant")
    fun getAllPlants(): Flow<List<PlantEntity>>

    @Query("Select * from Plant where id = :id" )
    suspend fun getPlantById(id:Int): PlantEntity?

    @Delete
    suspend fun deletePlant(plant: PlantEntity)
}