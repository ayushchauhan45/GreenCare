package com.ayush.plantwateringreminder.feature.plantfeature.Presentation.Plant_details.Component

sealed class PlantDetailsEvent {
     data object LikePlant: PlantDetailsEvent()
     data object UnLikePlant: PlantDetailsEvent()
}