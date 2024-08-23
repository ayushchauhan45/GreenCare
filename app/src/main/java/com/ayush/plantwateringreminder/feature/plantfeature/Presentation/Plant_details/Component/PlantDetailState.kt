package com.ayush.plantwateringreminder.feature.plantfeature.Presentation.Plant_details.Component

import com.ayush.plantwateringreminder.feature.plantfeature.Domain.Model.PlantDetails

data class PlantDetailState(
    val plantDetail: PlantDetails? = null,
    val isLoading:Boolean = false,
    val error:String? = null
)
