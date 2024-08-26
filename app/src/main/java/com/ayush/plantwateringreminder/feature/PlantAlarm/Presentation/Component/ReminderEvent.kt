package com.ayush.plantwateringreminder.feature.PlantAlarm.Presentation.Component

sealed class ReminderEvent {
    data object AddReminder : ReminderEvent()
    data object DeleteReminder: ReminderEvent()
    data object UpdateReminder: ReminderEvent()
}