package com.ayush.plantwateringreminder.feature.PlantAlarm.Data.DataSource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ayush.plantwateringreminder.feature.PlantAlarm.Domain.Model.Reminder

@Dao
interface ReminderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReminder(reminder:Reminder)

    @Query("Select * from Reminder where id = :id")
    suspend fun getReminderById(id:Int) : Reminder?

    @Delete
    suspend fun deleteReminder(reminder: Reminder)

}