package com.ayush.plantwateringreminder.feature.PlantDatabase.Presentation.My_Plants_Details

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteConstraintException
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ayush.plantwateringreminder.R
import com.ayush.plantwateringreminder.feature.PlantAlarm.Domain.Model.Reminder
import com.ayush.plantwateringreminder.feature.PlantAlarm.Domain.cancelAlarm
import com.ayush.plantwateringreminder.feature.PlantAlarm.Domain.schedulePeriodicAlarm
import com.ayush.plantwateringreminder.feature.PlantAlarm.Domain.setUpAlarm
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

    val context = LocalContext.current

    ModalBottomSheetLayout(sheetState = sheetState,
        sheetContent = {
                AlarmBottomSheetItem(
                    time = format.format(timeInMillis.value),
                    onTimeClick = {
                        isTimePickerVisible.value = true
                    })
                    {isRepeat->
                        state.myPlant?.id?.let { id->
                            val reminder = Reminder(
                                isWatered = false,
                                reminderTime = timeInMillis.value,
                                plantId = id,
                                isRepeat = isRepeat
                            )
                            try {
                                reminderViewModel.addReminder(reminder)
                                if(isRepeat){
                                    schedulePeriodicAlarm(context, reminder)
                                }else{
                                setUpAlarm(context, reminder)}
                            } catch (e: SQLiteConstraintException) {
                                Toast.makeText(context, "Failed to add reminder: Plant not found in database.", Toast.LENGTH_SHORT).show()
                            } catch (e: Exception) {
                                Toast.makeText(context, "An error occurred while adding the reminder.", Toast.LENGTH_SHORT).show()
                            }

                        }?:run{
                            Toast.makeText(context, "Plant not found, cannot set a reminder.", Toast.LENGTH_SHORT).show()
                        }

                        scope.launch {
                            sheetState.hide()
                        }
                    }
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

                    Spacer(modifier = Modifier.height(20.dp))

                    state.myPlant?.let { plantEntity ->
                        LikePlantDetailsItem(plantEntity = plantEntity)

                    }

                    Text(
                        text = "Schedule Watering Alarm",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(start = 5.dp)
                    )
                    Spacer(modifier = Modifier.height(50.dp))

                    val reminder = reminderState.reminder.firstOrNull()
                    if (reminder != null) {
                        AlarmCard(reminder = reminder, onDeleteClick = {
                            cancelAlarm(context, reminder)
                            reminderViewModel.deleteReminder(reminder)
                        }, onRepeatClick = {
                            cancelAlarm(context,reminder)
                            reminderViewModel.updateReminder(reminder.copy(
                                isWatered = true,
                                isRepeat = false
                            ))
                        },
                            time = "Reminder Time: ${format.format(reminder.reminderTime)}"
                        )

                    }
                    Spacer(modifier = Modifier.height(30.dp))

                    Button(onClick = {
                        scope.launch {
                            sheetState.show()
                        }
                    }, modifier = Modifier.fillMaxWidth()) {
                        Text(text = "Schedule the alarm")
                    }


                }
            }
        }

}

@Composable
fun AlarmCard(
    reminder: Reminder,
    time:String,
    onDeleteClick:()->Unit,
    onRepeatClick:()->Unit
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            ,

        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = "Reminder Set",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Text(
                    text = time,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(20.dp))

             if (reminder.isRepeat){
                 IconButton(onClick = { onRepeatClick.invoke() }) {
                     Icon(painter = painterResource(id = R.drawable.baseline_watch_later_24),
                         contentDescription = null )
                 }
             }
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
}












