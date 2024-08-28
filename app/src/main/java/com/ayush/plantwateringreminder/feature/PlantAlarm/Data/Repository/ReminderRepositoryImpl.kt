package com.ayush.plantwateringreminder.feature.PlantAlarm.Data.Repository

import com.ayush.plantwateringreminder.feature.PlantAlarm.Data.DataSource.ReminderDao
import com.ayush.plantwateringreminder.feature.PlantAlarm.Domain.Model.Reminder
import com.ayush.plantwateringreminder.feature.PlantAlarm.Domain.Repository.ReminderRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReminderRepositoryImpl @Inject constructor(
    private val dao: ReminderDao
): ReminderRepository{
    override suspend fun insertReminder(reminder: Reminder) {
         dao.insertReminder(reminder)
    }

    override  fun getReminder(): Flow<List<Reminder>> {
        return  dao.getReminder()
    }

    override suspend fun deleteReminder(reminder: Reminder) {
        dao.deleteReminder(reminder)
    }

    override suspend fun updateReminder(reminder: Reminder) {
        dao.updateReminder(reminder)
    }


}