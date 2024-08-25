package com.ayush.plantwateringreminder.feature.PlantAlarm.Presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayush.plantwateringreminder.feature.PlantAlarm.Domain.Repository.ReminderRepository
import com.ayush.plantwateringreminder.feature.PlantAlarm.Presentation.Component.ReminderEvent
import com.ayush.plantwateringreminder.feature.PlantAlarm.Presentation.Component.ReminderState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReminderViewModel @Inject constructor(
    private val repository: ReminderRepository,
    private val savedStateHandle: SavedStateHandle
):ViewModel(){

    var state by mutableStateOf(ReminderState())

    init {
        savedStateHandle.get<Int>("id")?.let {id->
            viewModelScope.launch {
                repository.getReminderById(id)?.also {
                    state = state.copy(
                        reminder = it
                    )
                }
            }
        }
    }

    fun onReminderEvent(event: ReminderEvent){
        when(event){
            ReminderEvent.AddReminder -> {
                viewModelScope.launch {
                    repository.insertReminder(
                        state.reminder ?: throw Exception("Reminder not found")
                    )
                }
            }
            ReminderEvent.DeleteReminder -> {
                viewModelScope.launch {
                    repository.deleteReminder(
                        state.reminder ?: throw Exception("Reminder not found")
                    )
                }
            }
        }
    }




}