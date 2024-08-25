package com.ayush.plantwateringreminder.feature.PlantAlarm.Data.Repository

import com.ayush.plantwateringreminder.feature.PlantAlarm.Data.DataSource.ReminderDao
import com.ayush.plantwateringreminder.feature.PlantAlarm.Domain.Model.Reminder
import com.ayush.plantwateringreminder.feature.PlantAlarm.Domain.Repository.ReminderRepository

class ReminderRepositoryImpl(
    private val dao: ReminderDao
): ReminderRepository{
    override suspend fun insertReminder(reminder: Reminder) {
         dao.insertReminder(reminder)
    }

    override suspend fun getReminderById(id: Int): Reminder? {
        return  dao.getReminderById(id)
    }

    override suspend fun deleteReminder(reminder: Reminder) {
        dao.deleteReminder(reminder)
    }
}