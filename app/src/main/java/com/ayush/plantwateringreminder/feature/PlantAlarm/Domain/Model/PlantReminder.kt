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
data class PlantReminder(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val plantId: Int,
    val reminderTime:Long
)
