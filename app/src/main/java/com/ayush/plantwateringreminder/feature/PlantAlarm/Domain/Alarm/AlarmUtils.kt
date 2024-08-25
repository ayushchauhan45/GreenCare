package com.ayush.plantwateringreminder.feature.PlantAlarm.Domain.Alarm

import android.content.Context
import com.ayush.plantwateringreminder.feature.PlantAlarm.Domain.Model.Reminder

interface AlarmUtils {

    fun scheduleAlarm(context:Context, reminder:Reminder)
    fun cancelAlarm(context:Context, reminder:Reminder)
}