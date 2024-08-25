package com.ayush.plantwateringreminder.feature.PlantAlarm.Data.DataSource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ayush.plantwateringreminder.feature.PlantAlarm.Domain.Model.PlantReminder
import kotlinx.coroutines.flow.Flow

@Dao
interface ReminderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReminder(reminder:PlantReminder)

    @Query("Select * from Reminder where id = :id")
    suspend fun getReminderById(id:Int) : PlantReminder?

    @Delete
    suspend fun deleteReminder(reminder: PlantReminder)

}