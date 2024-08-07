package com.ayush.plantwateringreminder.feature.plantfeature.Data.Remote.Model

data class PlantResponse(
    val current_page: Int,
    val `data`: List<PlantDto>?,
    val from: Int,
    val last_page: Int,
    val per_page: Int,
    val to: Int?,
    val total: Int
)
