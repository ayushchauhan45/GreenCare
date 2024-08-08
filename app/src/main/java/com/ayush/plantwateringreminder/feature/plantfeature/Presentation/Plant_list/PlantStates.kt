package com.ayush.plantwateringreminder.feature.plantfeature.Presentation.Plant_list

import com.ayush.plantwateringreminder.feature.plantfeature.Domain.Model.Plant
import com.ayush.plantwateringreminder.feature.plantfeature.util.Resource

data class PlantStates(
    val plantList:List<Plant> = emptyList(),
    val isLoading: Boolean = false,
    val searchQuery: String = ""
)
