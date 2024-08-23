package com.ayush.plantwateringreminder.feature.plantfeature.Presentation.Plant_list.Component

import com.ayush.plantwateringreminder.feature.plantfeature.Domain.Model.Plant

data class PlantStates(
    val plantList:List<Plant> = emptyList(),
    val isLoading: Boolean = false,
    val searchQuery: String = ""
)
