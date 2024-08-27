package com.ayush.plantwateringreminder.feature.PlantAlarm.Data.DataSource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ayush.plantwateringreminder.feature.PlantAlarm.Domain.Model.Reminder
import kotlinx.coroutines.flow.Flow

@Dao
interface ReminderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReminder(reminder:Reminder)

    @Query("Select * from Reminder ")
     fun getReminder() : Flow<List<Reminder>>

    @Delete
    suspend fun deleteReminder(reminder: Reminder)

    @Update
    suspend fun updateReminder(reminder: Reminder)



}