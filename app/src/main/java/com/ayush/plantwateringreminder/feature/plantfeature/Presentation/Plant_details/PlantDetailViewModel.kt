package com.ayush.plantwateringreminder.feature.plantfeature.Presentation.Plant_details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayush.plantwateringreminder.feature.plantfeature.Domain.Repository.PlantRepository
import com.ayush.plantwateringreminder.feature.plantfeature.util.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

class PlantDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: PlantRepository
) : ViewModel() {

    var state by mutableStateOf(PlantDetailState())

    init {
        viewModelScope.launch {
            val plantId = savedStateHandle.get<String>("id") ?: return@launch
            state =  state.copy(
                isLoading = true
            )
            val getPlantDetails = repository.getPlantDetails(plantId)
            when( getPlantDetails){
                is Resource.Error -> {
                    state =  state.copy(
                        plantDetail = null,
                        isLoading = false,
                        error = getPlantDetails.message
                    )
                }
                is Resource.Success -> {
                    state = state.copy(
                        plantDetail = getPlantDetails.data,
                        isLoading = false,
                        error = null
                    )

                }
                else -> Unit
            }
        }
    }
}