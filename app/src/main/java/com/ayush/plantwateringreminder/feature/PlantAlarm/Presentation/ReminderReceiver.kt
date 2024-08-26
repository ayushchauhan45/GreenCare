package com.ayush.plantwateringreminder.feature.PlantAlarm.Presentation

import android.Manifest.permission.POST_NOTIFICATIONS
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.ayush.plantwateringreminder.CHANNEL
import com.ayush.plantwateringreminder.R
import com.ayush.plantwateringreminder.feature.PlantAlarm.Data.Alarm.REMINDER
import com.ayush.plantwateringreminder.feature.PlantAlarm.Domain.Alarm.AlarmUtils
import com.ayush.plantwateringreminder.feature.PlantAlarm.Domain.Model.Reminder
import com.ayush.plantwateringreminder.feature.PlantAlarm.Domain.Repository.ReminderRepository
import com.ayush.plantwateringreminder.feature.PlantDatabase.Domain.Model.PlantEntity
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import okhttp3.internal.notify
import javax.inject.Inject

const val DONE = "DONE"
const val REJECT = "REJECT"

@AndroidEntryPoint
class ReminderReceiver:BroadcastReceiver() {

    @Inject
    lateinit var updateReminder: ReminderRepository

    @Inject
    lateinit var alarmUtils: AlarmUtils

    override  fun onReceive(context: Context, intent: Intent) {
       val reminderJson =intent.getStringExtra(REMINDER)
       val reminder = Gson().fromJson(reminderJson , Reminder::class.java)


        val doneIntent  = Intent(context, ReminderReceiver::class.java).apply { putExtra(
            REMINDER, reminderJson
        )
            action = DONE
        }


        val donePendingIntent = PendingIntent.getBroadcast(
            context,
            reminder.id.hashCode(),
            doneIntent,
            PendingIntent.FLAG_IMMUTABLE
        )
        val rejectIntent  = Intent(context, ReminderReceiver::class.java).apply { putExtra(
            REMINDER, reminderJson
        )
            action = REJECT
        }
        val rejectPendingIntent = PendingIntent.getBroadcast(
            context,
            reminder.id.hashCode(),
            rejectIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        when(intent.action){
            DONE ->{
                runBlocking { updateReminder.updateReminder(reminder.copy(isWatered = true)) }
                alarmUtils.cancelAlarm(context , reminder)
            }
            REJECT ->{
                runBlocking { updateReminder.updateReminder(reminder.copy(isWatered = true)) }
                alarmUtils.cancelAlarm(context , reminder)
            }

            else ->{
                if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.TIRAMISU){
                    if(ContextCompat.checkSelfPermission(
                            context,
                            POST_NOTIFICATIONS
                        ) == PackageManager.PERMISSION_GRANTED){


                        val notification = NotificationCompat.Builder(context, CHANNEL)
                            .setSmallIcon(R.drawable.logo)
                            .setContentTitle("Water the Plant")
                            .setContentText("It's time to water the plant")
                            .addAction(R.drawable.baseline_check_24,"DONE", donePendingIntent )
                            .addAction(R.drawable.baseline_close_24, "REJECT", rejectPendingIntent )
                            .build()

                        NotificationManagerCompat.from(context).notify(1, notification)

                    }
                }else{
                    val notification = NotificationCompat.Builder(context, CHANNEL)
                        .setSmallIcon(R.drawable.logo)
                        .setContentTitle("Water the Plant")
                        .setContentText("It's time to water the plant")
                        .addAction(R.drawable.baseline_check_24,"DONE", donePendingIntent )
                        .addAction(R.drawable.baseline_close_24, "REJECT", rejectPendingIntent )
                        .build()

                    NotificationManagerCompat.from(context).notify(1, notification)
                }
            }
        }



    }
}