package com.ayush.plantwateringreminder.feature.plantfeature.Domain.Model

import com.ayush.plantwateringreminder.feature.plantfeature.Data.Remote.Model.DefaultImageX
import com.ayush.plantwateringreminder.feature.plantfeature.Data.Remote.Model.DepthWaterRequirement
import com.ayush.plantwateringreminder.feature.plantfeature.Data.Remote.Model.Dimensions
import com.ayush.plantwateringreminder.feature.plantfeature.Data.Remote.Model.Hardiness
import com.ayush.plantwateringreminder.feature.plantfeature.Data.Remote.Model.HardinessLocation
import com.ayush.plantwateringreminder.feature.plantfeature.Data.Remote.Model.PlantAnatomy
import com.ayush.plantwateringreminder.feature.plantfeature.Data.Remote.Model.WateringGeneralBenchmark
import com.google.gson.annotations.SerializedName

data class PlantDetails(
    val common_name: String,
    val cycle: String,
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
    val small_url: String
)
