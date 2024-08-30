package com.ayush.plantwateringreminder.feature.PlantAlarm.Presentation.Reminder

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayush.plantwateringreminder.feature.PlantAlarm.Domain.Model.Reminder
import com.ayush.plantwateringreminder.feature.PlantAlarm.Domain.Repository.ReminderRepository
import com.ayush.plantwateringreminder.feature.PlantAlarm.Presentation.Reminder.Component.ReminderState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReminderViewModel @Inject constructor(
    private val repository: ReminderRepository,
):ViewModel(){

    var state by mutableStateOf(ReminderState())
    private var reminderJob : Job? = null

    init {
        getReminders()
    }


    fun addReminder(reminder:Reminder){
        viewModelScope.launch {
            repository.insertReminder(reminder)
        }
    }
    fun deleteReminder(reminder:Reminder){
        viewModelScope.launch {
            repository.deleteReminder(reminder)
        }
    }
    fun updateReminder(reminder:Reminder){
        viewModelScope.launch {
            repository.updateReminder(reminder)
        }
    }


     private fun getReminders(){
             reminderJob?.cancel()
             reminderJob = repository.getReminder().onEach { reminder->
                 state= state.copy(
                     reminder= reminder
                 )
             }.launchIn(viewModelScope)
     }






    }




