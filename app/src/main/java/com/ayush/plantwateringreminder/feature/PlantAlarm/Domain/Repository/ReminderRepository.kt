package com.ayush.plantwateringreminder.feature.PlantAlarm.Domain.Repository

import com.ayush.plantwateringreminder.feature.PlantAlarm.Domain.Model.Reminder

interface ReminderRepository {
    suspend fun insertReminder(reminder: Reminder)

    suspend fun getReminderById(id:Int): Reminder?

    suspend fun deleteReminder(reminder: Reminder)

    suspend fun updateReminder(reminder: Reminder)
}