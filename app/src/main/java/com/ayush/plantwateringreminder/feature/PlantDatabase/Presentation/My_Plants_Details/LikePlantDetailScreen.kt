package com.ayush.plantwateringreminder.feature.PlantDatabase.Presentation.My_Plants_Details

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.ModalBottomSheetLayout
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ayush.plantwateringreminder.feature.PlantAlarm.Domain.Model.Reminder
import com.ayush.plantwateringreminder.feature.PlantAlarm.Presentation.Reminder.AlarmBottomSheetItem
import com.ayush.plantwateringreminder.feature.PlantAlarm.Presentation.Reminder.ReminderViewModel
import com.ayush.plantwateringreminder.feature.PlantDatabase.Presentation.My_Plants_Details.Component.LikePlantDetailsItem
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LikePlantDetailsScreen(
    navController: NavController,
    id:Int?,
    viewModel: LikePlantDetailViewModel = hiltViewModel(),
    reminderViewModel: ReminderViewModel = hiltViewModel(),
) {

    var isLiked by rememberSaveable { mutableStateOf(false) }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    val context = LocalContext.current

    val reminderState = reminderViewModel.state

    val state = viewModel.state
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )
    val scope = rememberCoroutineScope()

    val isTimePickerVisible = remember {
        mutableStateOf(false)
    }
    val timePickerState = rememberTimePickerState()

    val format = remember {
        SimpleDateFormat("hh:mm a", Locale.getDefault())
    }

    val timeInMillis = remember {
        mutableStateOf(0L)
    }
    val initialSelectedDaysOfWeek = 0

    ModalBottomSheetLayout(sheetState = sheetState,
        sheetContent = {
                 AlarmBottomSheetItem(
                     time = format.format(timeInMillis.value),
                     selectedDaysOfWeek = initialSelectedDaysOfWeek,
                     onTimeClick = {
                         isTimePickerVisible.value = true
                     },
                     onClick = { selectedDay ->
                         val reminder = Reminder(
                             isWatered = false,
                             reminderTime = timeInMillis.value,
                             daysOfWeek = selectedDay,
                             plantId = state.myPlant?.id
                                 ?: throw Exception("Plant not found")
                         )
                         reminderViewModel.addReminder(reminder)
                         reminderViewModel.scheduleAlarm(context, reminder)
                         scope.launch {
                             sheetState.hide()
                         }
                     }
                 )

        }
    )
    {

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = { Text(text = "Plant Details") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        isLiked = !isLiked
                    }
                    ) {
                        Icon(
                            imageVector = if (isLiked) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                            contentDescription = null
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) { padding ->

        if (isTimePickerVisible.value) {
            Dialog(onDismissRequest = { }) {
                Column {
                    TimePicker(state = timePickerState)
                    Row {
                        Button(onClick = {
                            isTimePickerVisible.value = isTimePickerVisible.value.not()
                        }) {
                            Text(text = "Cancel")
                        }
                        Button(onClick = {
                            val calendar = Calendar.getInstance().apply {
                                set(Calendar.HOUR_OF_DAY, timePickerState.hour)
                                set(Calendar.MINUTE, timePickerState.minute)
                            }
                            timeInMillis.value = calendar.timeInMillis
                            isTimePickerVisible.value = false
                        }) {
                            Text(text = "Confirm")
                        }
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            Button(onClick = {
                scope.launch {
                    sheetState.show()
                }
            }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Schedule the alarm")
            }
            Spacer(modifier = Modifier.height(20.dp))

            state.myPlant?.let { plantEntity ->
                LikePlantDetailsItem(plantEntity = plantEntity)
            }
            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "Schedule Watering Alarm",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(start = 5.dp)
            )
            Spacer(modifier = Modifier.height(50.dp))

                val reminder = reminderState.reminder.firstOrNull()
                if (reminder != null) {
                    AlarmCard(reminder = reminder, onClickable = {
                        scope.launch {
                            sheetState.show()
                        }
                    }, onDeleteClick = {
                        reminderViewModel.cancelAlarm(context, reminder)
                        reminderViewModel.deleteReminder(reminder)
                    },
                        time = "Reminder Time: ${format.format(reminder.reminderTime)}"
                    )

                }



        }
    }
 }
}

@Composable
fun AlarmCard(
    reminder: Reminder,
    time:String,
    onClickable:()->Unit,
    onDeleteClick:()->Unit
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                onClickable.invoke()
            },

        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {

                    val days = listOf(
                        "M" to Reminder.MONDAY,
                        "T" to Reminder.TUESDAY,
                        "W" to Reminder.WEDNESDAY,
                        "T" to Reminder.THURSDAY,
                        "F" to Reminder.FRIDAY,
                        "S" to Reminder.SATURDAY,
                        "S" to Reminder.SUNDAY
                    )
                    days.forEach { (label, dayBitmask) ->
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(

                                    if (Reminder.isDaysSelected(
                                            reminder.daysOfWeek,
                                            dayBitmask
                                        )
                                    ) Color.White
                                    else Color.LightGray.copy(alpha = 0.3f)
                                )
                        ) {
                            Text(
                                text = if (reminder.isWatered) {
                                    "Watered on $label"
                                } else label,
                                color = if (reminder.isWatered.not()) {
                                    if (Reminder.isDaysSelected(
                                            reminder.daysOfWeek,
                                            dayBitmask
                                        )
                                    ) Color.Black
                                    else Color.DarkGray
                                } else {
                                    Color.Black
                                },
                                fontWeight = FontWeight.Bold
                            )

                        }
                    }
                }

            }
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Text(
                    text = time,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(20.dp))
                IconButton(onClick = {
                onDeleteClick.invoke()
                }) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = null
                    )
                }
            }
        }
    }












