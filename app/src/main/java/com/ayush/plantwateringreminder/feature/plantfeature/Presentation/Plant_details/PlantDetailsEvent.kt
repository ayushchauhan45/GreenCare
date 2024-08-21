package com.ayush.plantwateringreminder.feature.plantfeature.Presentation.Plant_details

sealed class PlantDetailsEvent {
     data object LikePlant: PlantDetailsEvent()
}