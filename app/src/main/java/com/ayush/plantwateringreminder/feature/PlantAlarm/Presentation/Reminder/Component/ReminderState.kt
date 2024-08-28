package com.ayush.plantwateringreminder.feature.PlantAlarm.Presentation.Reminder.Component

import com.ayush.plantwateringreminder.feature.PlantAlarm.Domain.Model.Reminder

data class ReminderState(
    val reminder: List<Reminder> = emptyList()
)