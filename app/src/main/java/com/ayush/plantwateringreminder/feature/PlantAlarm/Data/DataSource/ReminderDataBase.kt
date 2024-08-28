package com.ayush.plantwateringreminder.feature.PlantAlarm.Data.DataSource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ayush.plantwateringreminder.feature.PlantAlarm.Domain.Model.Reminder
import com.ayush.plantwateringreminder.feature.PlantDatabase.Domain.Model.PlantEntity

@Database(
    entities = [Reminder::class, PlantEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ReminderDataBase:RoomDatabase() {
    abstract val reminderDao:ReminderDao
    companion object{
        const val DATABASE_NAME = "reminder_db"
    }
}