package com.ayush.plantwateringreminder.feature.PlantAlarm.Presentation.Component

import com.ayush.plantwateringreminder.feature.PlantAlarm.Domain.Model.Reminder

data class ReminderState(
    val reminder: List<Reminder> = emptyList()
)