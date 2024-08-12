package com.ayush.plantwateringreminder.feature.plantfeature.Domain.Model


data class Plant(
    val common_name: String,
    val default_image: DefaultImages,
    val watering: String,
    val id: Int,
)


data class DefaultImages(
    val thumbnail: String
)