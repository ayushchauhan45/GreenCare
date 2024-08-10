package com.ayush.plantwateringreminder.feature.plantfeature.Presentation.Plant_list

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayush.plantwateringreminder.feature.plantfeature.Domain.Repository.PlantRepository
import com.ayush.plantwateringreminder.feature.plantfeature.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlantListViewModel @Inject constructor(
    private val repository: PlantRepository
):ViewModel()
{
    var state by mutableStateOf(PlantStates())
    private var searchJob: Job? = null
    init {
      getPlantList()
    }

    fun onEvent(event: PlantListEvent){
        when(event){
            is PlantListEvent.Refresh -> {
             getPlantList()
            }

            is PlantListEvent.OnSearchQueryChange -> {
                state = state.copy(
                    searchQuery = event.query
                )
                searchJob?.cancel()
                searchJob  = viewModelScope.launch {
                    delay(500L)
                    getPlantList()
                }
            }
        }
    }

    private fun getPlantList(
         query:String = state.searchQuery.lowercase()
    ){
     viewModelScope.launch {
         repository.getPlantList(query)
             .collect{result ->
                 when(result){
                     is Resource.Error -> Unit

                     is Resource.Loading -> {
                         state = state.copy(
                             isLoading = result.isLoading
                         )
                     }
                     is Resource.Success -> {
                         Log.d("PlantListViewModel", "Success: ${result.data}")
                         result.data?.let { plantList ->
                            state = state.copy(
                                plantList = plantList
                            )

                        }
                     }
                 }

             }
     }
    }
}