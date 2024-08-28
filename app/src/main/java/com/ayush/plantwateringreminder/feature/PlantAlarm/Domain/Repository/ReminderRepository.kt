package com.ayush.plantwateringreminder.feature.PlantAlarm.Domain.Repository

import com.ayush.plantwateringreminder.feature.PlantAlarm.Domain.Model.Reminder
import kotlinx.coroutines.flow.Flow

interface ReminderRepository {
    suspend fun insertReminder(reminder: Reminder)

     fun getReminder(): Flow<List<Reminder>>

    suspend fun deleteReminder(reminder: Reminder)

    suspend fun updateReminder(reminder: Reminder)


}