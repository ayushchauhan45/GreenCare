package com.ayush.plantwateringreminder.feature.PlantAlarm.Data.Repository

import com.ayush.plantwateringreminder.feature.PlantAlarm.Data.DataSource.ReminderDao
import com.ayush.plantwateringreminder.feature.PlantAlarm.Domain.Model.PlantReminder
import com.ayush.plantwateringreminder.feature.PlantAlarm.Domain.Repository.ReminderRepository
import kotlinx.coroutines.flow.Flow

class ReminderRepositoryImpl(
    private val dao: ReminderDao
): ReminderRepository{
    override suspend fun insertReminder(reminder: PlantReminder) {
         dao.insertReminder(reminder)
    }

    override suspend fun getReminderById(id: Int): PlantReminder? {
        return  dao.getReminderById(id)
    }

    override suspend fun deleteReminder(reminder: PlantReminder) {
        dao.deleteReminder(reminder)
    }
}