package com.ayush.plantwateringreminder.feature.plantfeature.Presentation.Plant_list.Component


sealed class PlantListEvent {
    data class OnSearchQueryChange(val query: String) : PlantListEvent()
}