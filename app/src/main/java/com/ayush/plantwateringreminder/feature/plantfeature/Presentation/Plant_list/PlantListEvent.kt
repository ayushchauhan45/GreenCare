package com.ayush.plantwateringreminder.feature.plantfeature.Presentation.Plant_list

import com.ayush.plantwateringreminder.feature.plantfeature.Domain.Model.Plant

sealed class PlantListEvent {
    data object Refresh:PlantListEvent()
    data class OnSearchQueryChange(val query: String) : PlantListEvent()

}