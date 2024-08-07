package com.ayush.plantwateringreminder.feature.plantfeature.Domain.Model

import com.ayush.plantwateringreminder.feature.plantfeature.Data.Remote.Model.DefaultImage

data class Plant(
    val common_name: String,
    val default_image: DefaultImage,
    val id: Int,
    val sunlight: List<String>,
    val watering: String
)
