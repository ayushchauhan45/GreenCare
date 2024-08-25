package com.ayush.plantwateringreminder.feature.plantfeature.Presentation.Plant_details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayush.plantwateringreminder.feature.PlantDatabase.Domain.Repository.PlantWateringRepository
import com.ayush.plantwateringreminder.feature.plantfeature.Domain.Repository.PlantRepository
import com.ayush.plantwateringreminder.feature.plantfeature.Presentation.Plant_details.Component.PlantDetailState
import com.ayush.plantwateringreminder.feature.plantfeature.Presentation.Plant_details.Component.PlantDetailsEvent
import com.ayush.plantwateringreminder.feature.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlantDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: PlantRepository,
    private val wateringRepository: PlantWateringRepository
) : ViewModel()
{

    var state by mutableStateOf(PlantDetailState())
    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()


    init {
        viewModelScope.launch {
            val plantId = savedStateHandle.get<Int>("id") ?: return@launch
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

    fun onEvent(event: PlantDetailsEvent){
        when(event){
            is PlantDetailsEvent.LikePlant->{
                viewModelScope.launch {
                    try {
                        wateringRepository.insertPlant(
                            plant = state.plantDetail ?: throw Exception("Plant not found")
                        )
                        _eventFlow.emit(UiEvent.LikePlant)
                        _eventFlow.emit(UiEvent.ShowSnackBar("Plant Added to Favourite"))
                    } catch (e: Exception) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackBar(
                                message = e.message ?: "Unknown error"
                            )
                        )
                    }
                }
            }

            PlantDetailsEvent.UnLikePlant -> {
                viewModelScope.launch {
                    wateringRepository.deletePlant(
                        plant = state.plantDetail?: throw Exception("Plant not found")
                    )
                }
            }
        }
    }
    sealed class UiEvent{
        data class ShowSnackBar(val message:String):UiEvent()
        data object LikePlant: UiEvent()

    }
}


