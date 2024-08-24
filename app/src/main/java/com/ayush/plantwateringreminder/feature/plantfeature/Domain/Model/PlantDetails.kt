package com.ayush.plantwateringreminder.feature.plantfeature.Domain.Model



data class PlantDetails(
    val common_name: String,
    val description: String,
    val id: Int,
    val soil: List<Any>,
    val default_image: DefaultImagesX,
    val volume_water_requirement: List<Any>,
    val watering: String,
    val watering_general_benchmark: WateringGeneralBenchmarks,
    val watering_period: Any
)
data class WateringGeneralBenchmarks(
    val unit: String,
    val value: String
)
data class DefaultImagesX(
    val original_url: String,
    val thumbnail: String

)
