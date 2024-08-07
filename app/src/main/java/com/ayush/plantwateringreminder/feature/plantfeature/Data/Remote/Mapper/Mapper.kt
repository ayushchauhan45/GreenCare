package com.ayush.plantwateringreminder.feature.plantfeature.Data.Remote.Mapper

import com.ayush.plantwateringreminder.feature.plantfeature.Data.Remote.Model.PlantDto
import com.ayush.plantwateringreminder.feature.plantfeature.Data.Remote.Model.PlantDtoDetail
import com.ayush.plantwateringreminder.feature.plantfeature.Data.Remote.Model.WateringGeneralBenchmark
import com.ayush.plantwateringreminder.feature.plantfeature.Domain.Model.Plant
import com.ayush.plantwateringreminder.feature.plantfeature.Domain.Model.PlantDetails
import com.ayush.plantwateringreminder.feature.plantfeature.Domain.Model.WateringGeneralBenchmarks

fun List<PlantDto>.toDomain():List<Plant> = map{
    Plant(
        common_name = it.common_name,
        default_image = it.default_image,
        id = it.id,
        sunlight = it.sunlight,
        watering = it.watering
    )
}


fun PlantDtoDetail.toDomain():PlantDetails {
    return PlantDetails(
        common_name = common_name,
        cycle = cycle,
        description = description,
        id = id,
        scientific_name = scientific_name,
        soil = soil,
        volume_water_requirement = volume_water_requirement,
        watering = watering,
        watering_general_benchmark = watering_general_benchmark.toDomain(),
        watering_period = watering_period
    )
}
fun WateringGeneralBenchmark.toDomain(): WateringGeneralBenchmarks {
    return WateringGeneralBenchmarks(
        unit = this.unit,
        value = this.value
    )
}