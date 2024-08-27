package com.ayush.plantwateringreminder.feature.PlantAlarm.Presentation

//noinspection UsingMaterialAndMaterial3Libraries
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ayush.plantwateringreminder.feature.PlantAlarm.Domain.Model.Reminder


@SuppressLint("SuspiciousIndentation")
@Composable
fun AlarmBottomSheetItem(
    time:String,
    selectedDaysOfWeek: Int,
    onTimeClick : ()->Unit,
    onDayClick: ()->Unit,
    onClick: ()->Unit
){
    Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = "Set a Reminder",
                color = Color(0xFF038530),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(24.dp))

            Row{
             OutlinedTextField(value = time, onValueChange = {},
                 modifier = Modifier
                     .width(200.dp)
                     .padding(8.dp)
                     .clickable {
                         onTimeClick.invoke()
                     },enabled = false)
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "Choose the time for watering",
                    fontWeight = FontWeight.Thin)

            }
            Spacer(modifier = Modifier.height(18.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ){

                val day = listOf(
                    "M" to Reminder.MONDAY,
                    "T" to Reminder.TUESDAY,
                    "W" to Reminder.WEDNESDAY,
                    "Th" to Reminder.THURSDAY,
                    "F" to Reminder.FRIDAY,
                    "S" to Reminder.SATURDAY,
                    "S" to Reminder.SUNDAY
                )

                day.forEach {(label, dayBitmask)->

                val isSelected = Reminder.isDaysSelected(selectedDaysOfWeek,dayBitmask)
                    Text(text = label, modifier = Modifier.padding(8.dp).background(
                        if(isSelected) Color.White else Color.LightGray,
                        shape = CircleShape
                    ).clickable { onDayClick.invoke() }.padding(8.dp),
                        color = if(isSelected) Color.Black else Color.LightGray

                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "Choose the day for watering",
                    fontWeight = FontWeight.Thin
                )

            }

            Column(
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.End
            ) {
                Button(onClick = { onClick.invoke() },
                    modifier = Modifier.padding(
                        end = 25.dp,
                        bottom = 25.dp
                    )) {
                    Text(text = "Save")
                }
            }
        Spacer(modifier = Modifier.height(32.dp))

        }
}