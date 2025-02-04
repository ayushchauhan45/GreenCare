package com.ayush.plantwateringreminder.feature.PlantAlarm.Presentation.Reminder

//noinspection UsingMaterialAndMaterial3Libraries
import android.annotation.SuppressLint
import android.widget.ToggleButton
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
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ayush.plantwateringreminder.feature.PlantAlarm.Domain.Model.Reminder


@SuppressLint("SuspiciousIndentation")
@Composable
fun AlarmBottomSheetItem(
    time: String,
    onTimeClick: () -> Unit,
    onClick: (Boolean) -> Unit
) {

   val isChecked = remember {
       mutableStateOf(false)
   }
    Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = "Set a Reminder",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(24.dp))
        Text(text = "Choose the time for watering",
            fontWeight = FontWeight.SemiBold)

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(value = time, onValueChange = {},
                modifier = Modifier
                    .width(200.dp)
                    .padding(8.dp)
                    .clickable {
                        onTimeClick.invoke()
                    },enabled = false)

        Spacer(modifier = Modifier.height(18.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ){
            Text(text = "Schedule Alarm ",
                fontWeight = FontWeight.SemiBold)

            Spacer(modifier = Modifier.width(16.dp))

            Switch(checked = isChecked.value, onCheckedChange = {
                isChecked.value = it
            })

        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = { onClick.invoke(
            isChecked.value
        ) },
                    modifier = Modifier.padding(
                        end = 25.dp,
                        bottom = 25.dp
                    )) {
                    Text(text = "Save")
                }
        }
}