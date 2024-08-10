package com.ayush.plantwateringreminder.feature.plantfeature.Domain.Model

import com.ayush.plantwateringreminder.feature.plantfeature.Data.Remote.Model.DefaultImage

data class Plant(
    val common_name: String,
    val default_image: DefaultImages,
    val watering: String,
    val id: Int,
)


data class DefaultImages(
    val thumbnail: String
)