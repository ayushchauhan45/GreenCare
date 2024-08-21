package com.ayush.plantwateringreminder.feature.WaterRemindinngFeature.Presentation.My_Plants

import com.ayush.plantwateringreminder.feature.WaterRemindinngFeature.Domain.Model.PlantEntity

data class LikePlantsState(
    val myPlants: List<PlantEntity> = emptyList()
)
