package com.ayush.plantwateringreminder.feature.PlantDatabase.Data.DataSource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ayush.plantwateringreminder.feature.PlantAlarm.Data.DataSource.ReminderDao
import com.ayush.plantwateringreminder.feature.PlantAlarm.Domain.Model.Reminder
import com.ayush.plantwateringreminder.feature.PlantDatabase.Domain.Model.PlantEntity

@Database(
entities = [PlantEntity::class, Reminder::class],
version = 2,
 exportSchema = false
)
abstract class PlantDatabase:RoomDatabase() {
abstract val plantDao: PlantDao
abstract val reminderDao: ReminderDao

companion object{
    const val DATABASE_NAME = "plant_db"
}

}