package com.ayush.plantwateringreminder.feature.plantfeature.Presentation.Plant_list


sealed class PlantListEvent {
    data class OnSearchQueryChange(val query: String) : PlantListEvent()

}