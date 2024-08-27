package com.ayush.plantwateringreminder.feature.PlantDatabase.Presentation.My_Plants_Details

import android.annotation.SuppressLint
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ayush.plantwateringreminder.feature.PlantAlarm.Domain.Model.Reminder
import com.ayush.plantwateringreminder.feature.PlantAlarm.Presentation.ReminderViewModel
import com.ayush.plantwateringreminder.feature.PlantDatabase.Presentation.My_Plants_Details.Component.LikePlantDetailsItem


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LikePlantDetailsScreen(
    navController: NavController,
    id:Int?,
    viewModel: LikePlantDetailViewModel = hiltViewModel(),
    reminderViewModel: ReminderViewModel= hiltViewModel()
) {

    var isLiked by rememberSaveable { mutableStateOf(false) }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    val reminderState= reminderViewModel.state
    val state = viewModel.state

    Scaffold (
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(title = { Text(text = "Plant Details") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                    }
                },
                actions = {
                    IconButton(onClick = {
                        isLiked = !isLiked
                    }
                    ) {
                        Icon(imageVector = if(isLiked) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder, contentDescription = null)
                    }
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) {padding->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
            ) {

                    state.myPlant?.let { plantEntity ->
                        LikePlantDetailsItem(plantEntity = plantEntity)
                    }
                    Spacer(modifier = Modifier.height(30.dp))

                    Text(text = "Schedule Watering Alarm",
                        Modifier
                            .size(40.dp)
                            .padding(
                                start = 20.dp
                            )
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                val singleReminder = reminderState.reminder.firstOrNull()
                if(singleReminder != null) {
                    ReminderCard(reminder = singleReminder)
                }

            }
    }

}

@Composable
fun ReminderCard(reminder: Reminder) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            // Format and display the reminder time
            Text(
                text = "Reminder Time: ${reminder.reminderTime}",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))

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
                            text = label,
                            color = if (Reminder.isDaysSelected(reminder.daysOfWeek, dayBitmask)) Color.Black
                            else Color.DarkGray,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}


