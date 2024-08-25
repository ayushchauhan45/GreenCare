package com.ayush.plantwateringreminder.feature.PlantDatabase.Presentation.My_Plants_Details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayush.plantwateringreminder.feature.PlantDatabase.Domain.Repository.PlantWateringRepository
import com.ayush.plantwateringreminder.feature.PlantDatabase.Presentation.My_Plants_Details.Component.LikePlantDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LikePlantDetailViewModel @Inject constructor(
    private val  repository: PlantWateringRepository,
    private val savedStateHandle: SavedStateHandle
):ViewModel(){

    var state by mutableStateOf(LikePlantDetailState())
    private var  currentPlantId:Int? = null

    init {
        savedStateHandle.get<Int>("id")?.let { id->
            viewModelScope.launch {
                if (id != -1) {
                    repository.getPlantById(
                        id
                    )?.also { plant ->
                        currentPlantId = plant.id
                        state = state.copy(
                            myPlant = plant
                        )
                    }
                }
            }
        }
    }

}