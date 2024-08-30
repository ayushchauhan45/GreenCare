package com.ayush.plantwateringreminder.feature.PlantAlarm.Domain

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.ayush.plantwateringreminder.ReminderReceiver
import com.ayush.plantwateringreminder.feature.PlantAlarm.Domain.Model.Reminder
import com.google.gson.Gson


const val REMINDER = "REMINDER"


 fun setUpAlarm(context: Context, reminder: Reminder) {

    val intent = Intent(context, ReminderReceiver::class.java).apply {
        putExtra(REMINDER, Gson().toJson(reminder))
    }

    val pendingIntent = PendingIntent.getBroadcast(
        context , reminder.reminderTime.toInt() ,intent,
        PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
    )
     val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

     try {
        alarmManager.setExact(AlarmManager.RTC_WAKEUP,reminder.reminderTime,pendingIntent)
    }catch (e:SecurityException){
        e.printStackTrace()
    }

}



 fun cancelAlarm(context: Context, reminder: Reminder) {

    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager


    val intent = Intent(context, ReminderReceiver::class.java).apply {
        putExtra(REMINDER, Gson().toJson(reminder))
    }

    val pendingIntent = PendingIntent.getBroadcast(
        context , reminder.reminderTime.toInt() ,intent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )
    try {
        alarmManager.cancel(pendingIntent)
    }catch (e:SecurityException){
        e.printStackTrace()
    }

}

 fun schedulePeriodicAlarm(context: Context, reminder: Reminder) {
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    val intent = Intent(context, ReminderReceiver::class.java).apply {
        putExtra(REMINDER, Gson().toJson(reminder))
    }

    val pendingIntent = PendingIntent.getBroadcast(
        context , reminder.reminderTime.toInt() ,intent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    try {
        val schedulePeriod = 2L*60L*1000L
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,reminder.reminderTime,schedulePeriod,pendingIntent)
    }catch (e:SecurityException){
        e.printStackTrace()
    }
}