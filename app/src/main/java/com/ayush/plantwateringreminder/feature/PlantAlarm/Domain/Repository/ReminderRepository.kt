package com.ayush.plantwateringreminder.feature.PlantAlarm.Domain.Repository

import com.ayush.plantwateringreminder.feature.PlantAlarm.Domain.Model.PlantReminder
import kotlinx.coroutines.flow.Flow

interface ReminderRepository {
    suspend fun insertReminder(reminder: PlantReminder)

    fun getReminderById(id:Int): Flow<PlantReminder>

    suspend fun deleteReminder(reminder: PlantReminder)
}