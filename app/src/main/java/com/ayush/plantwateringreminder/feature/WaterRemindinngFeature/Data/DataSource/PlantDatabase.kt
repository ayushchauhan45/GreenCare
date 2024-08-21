package com.ayush.plantwateringreminder.feature.WaterRemindinngFeature.Data.DataSource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ayush.plantwateringreminder.feature.WaterRemindinngFeature.Domain.Model.PlantEntity

@Database(
entities = [PlantEntity::class],
version = 1,
 exportSchema = false
)
abstract class PlantDatabase:RoomDatabase() {
abstract val plantDao: PlantDao
companion object{
    const val DATABASE_NAME = "plant_db"
}

}