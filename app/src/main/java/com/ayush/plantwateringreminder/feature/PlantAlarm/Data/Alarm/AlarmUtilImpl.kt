package com.ayush.plantwateringreminder.feature.PlantAlarm.Data.Alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.ayush.plantwateringreminder.feature.PlantAlarm.Domain.Alarm.AlarmUtils
import com.ayush.plantwateringreminder.feature.PlantAlarm.Domain.Model.Reminder
import com.ayush.plantwateringreminder.feature.PlantAlarm.Presentation.ReminderReceiver
import com.google.gson.Gson
import java.util.Calendar

const val REMINDER = "REMINDER"

class AlarmUtilImpl: AlarmUtils {

    override fun scheduleAlarm(context: Context, reminder: Reminder) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val calendar = Calendar.getInstance().apply {
            timeInMillis = reminder.reminderTime
        }

        val hour = calendar.get(
            Calendar.HOUR_OF_DAY
        )

        val minute = calendar.get(
            Calendar.MINUTE
        )
        
        val daysOfWeek = listOf(
            Calendar.MONDAY to Reminder.MONDAY,
            Calendar.TUESDAY to Reminder.TUESDAY,
            Calendar.WEDNESDAY to Reminder.WEDNESDAY,
            Calendar.THURSDAY to Reminder.THURSDAY,
            Calendar.FRIDAY to Reminder.FRIDAY,
            Calendar.SATURDAY to Reminder.SATURDAY,
            Calendar.SUNDAY to Reminder.SUNDAY
        )
        for ((calendarDay, dayBitmark) in daysOfWeek){
            if (reminder.daysOfWeek and dayBitmark  != 0){

                val intent = Intent(context, ReminderReceiver::class.java).apply {
                    putExtra(REMINDER, Gson().toJson(reminder))
                }

                val pendingIntent = PendingIntent.getBroadcast(
                        context , reminder.id.hashCode() + calendarDay,intent,
                        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )
                 val alarmCalendar = Calendar.getInstance().apply {
                     set(Calendar.HOUR_OF_DAY,hour)
                     set(Calendar.MINUTE,minute)
                     set(Calendar.SECOND, 0)
                     set(Calendar.DAY_OF_WEEK, calendarDay)

                     if (before(Calendar.getInstance())){
                         add(Calendar.WEEK_OF_YEAR,1)
                     }
                 }
                try {
                    alarmManager.setExact(AlarmManager.RTC_WAKEUP,alarmCalendar.timeInMillis,pendingIntent)
                }catch (e:SecurityException){
                    e.printStackTrace()
                }

            }
        }
    }

    override fun cancelAlarm(context: Context, reminder: Reminder) {

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager


        val daysOfWeek = listOf(
            Calendar.MONDAY to Reminder.MONDAY,
            Calendar.TUESDAY to Reminder.TUESDAY,
            Calendar.WEDNESDAY to Reminder.WEDNESDAY,
            Calendar.THURSDAY to Reminder.THURSDAY,
            Calendar.FRIDAY to Reminder.FRIDAY,
            Calendar.SATURDAY to Reminder.SATURDAY,
            Calendar.SUNDAY to Reminder.SUNDAY
        )
        for ((calendarDay, dayBitmark) in daysOfWeek){
            if (reminder.daysOfWeek and dayBitmark  != 0){

                val intent = Intent(context, ReminderReceiver::class.java).apply {
                    putExtra(REMINDER, Gson().toJson(reminder))
                }

                val pendingIntent = PendingIntent.getBroadcast(
                    context , reminder.id.hashCode() + calendarDay,intent,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )

                try {
                    alarmManager.cancel(pendingIntent)
                }catch (e:SecurityException){
                    e.printStackTrace()
                }

            }
        }

    }
}