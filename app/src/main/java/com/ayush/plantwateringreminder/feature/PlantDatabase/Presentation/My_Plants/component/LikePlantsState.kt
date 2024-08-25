package com.ayush.plantwateringreminder.feature.PlantDatabase.Presentation.My_Plants.component

import com.ayush.plantwateringreminder.feature.PlantDatabase.Domain.Model.PlantEntity

data class LikePlantsState(
    val myPlants: List<PlantEntity> = emptyList()
)
