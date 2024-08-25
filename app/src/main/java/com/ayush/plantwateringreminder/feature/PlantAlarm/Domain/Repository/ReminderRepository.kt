package com.ayush.plantwateringreminder.feature.PlantAlarm.Domain.Repository

import com.ayush.plantwateringreminder.feature.PlantAlarm.Domain.Model.PlantReminder

interface ReminderRepository {
    suspend fun insertReminder(reminder: PlantReminder)

    suspend fun getReminderById(id:Int): PlantReminder?

    suspend fun deleteReminder(reminder: PlantReminder)
}