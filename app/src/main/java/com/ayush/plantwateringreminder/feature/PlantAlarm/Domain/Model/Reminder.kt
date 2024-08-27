package com.ayush.plantwateringreminder.feature.PlantAlarm.Domain.Model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.ayush.plantwateringreminder.feature.PlantDatabase.Domain.Model.PlantEntity

@Entity(tableName = "Reminder",
    foreignKeys = [ForeignKey(
        entity = PlantEntity::class,
        parentColumns = ["id"],
        childColumns = ["plantId"],
        onDelete = ForeignKey.CASCADE)
    ],
    indices = [Index("id")])
data class Reminder(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val plantId: Int,
    val isWatered: Boolean = false,
    val reminderTime:Long,
    val daysOfWeek: Int
){
    companion object{
        val MONDAY = 1 shl 0
        val TUESDAY = 1 shl 1
        val WEDNESDAY = 1 shl 2
        val THURSDAY = 1 shl 3
        val FRIDAY = 1 shl 4
        val SATURDAY = 1 shl 5
        val SUNDAY = 1 shl 6

        fun isDaysSelected(daysOfWeekBitmask: Int, dayBitmask: Int):Boolean {
         return daysOfWeekBitmask and dayBitmask != 0
        }
    }

}
