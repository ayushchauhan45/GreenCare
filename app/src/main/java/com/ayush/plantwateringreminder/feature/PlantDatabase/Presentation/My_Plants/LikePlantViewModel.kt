package com.ayush.plantwateringreminder.feature.PlantDatabase.Presentation.My_Plants

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayush.plantwateringreminder.feature.PlantDatabase.Domain.Repository.PlantWateringRepository
import com.ayush.plantwateringreminder.feature.PlantDatabase.Presentation.My_Plants.component.LikePlantsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LikePlantViewModel @Inject constructor(
    private val repository: PlantWateringRepository
):ViewModel() {
    var state by mutableStateOf(LikePlantsState())
    private var plantJob: Job? =null



    init {
         getNote()
    }

     private fun getNote(){
         plantJob?.cancel()
         plantJob = repository.getPlant()
            .onEach {plant->
           state = state.copy(
               myPlants = plant
           )
            }.launchIn(viewModelScope)


     }
}