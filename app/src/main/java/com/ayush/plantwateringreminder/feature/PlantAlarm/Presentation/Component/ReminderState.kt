package com.ayush.plantwateringreminder.feature.PlantAlarm.Presentation.Component

import com.ayush.plantwateringreminder.feature.PlantAlarm.Domain.Model.PlantReminder

data class ReminderState(
    val reminder: PlantReminder? = null
)