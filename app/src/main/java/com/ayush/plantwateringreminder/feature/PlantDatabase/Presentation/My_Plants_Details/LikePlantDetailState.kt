package com.ayush.plantwateringreminder.feature.PlantDatabase.Presentation.My_Plants_Details

import com.ayush.plantwateringreminder.feature.PlantDatabase.Domain.Model.PlantEntity

data class LikePlantDetailState(
    val myPlant: PlantEntity? = null
)
