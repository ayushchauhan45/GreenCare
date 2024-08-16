package com.ayush.plantwateringreminder.feature.plantfeature.Data.Remote.Mapper

import com.ayush.plantwateringreminder.feature.plantfeature.Data.Remote.Model.DefaultImage
import com.ayush.plantwateringreminder.feature.plantfeature.Data.Remote.Model.DefaultImageX
import com.ayush.plantwateringreminder.feature.plantfeature.Data.Remote.Model.PlantDto
import com.ayush.plantwateringreminder.feature.plantfeature.Data.Remote.Model.PlantDtoDetail
import com.ayush.plantwateringreminder.feature.plantfeature.Data.Remote.Model.WateringGeneralBenchmark
import com.ayush.plantwateringreminder.feature.plantfeature.Domain.Model.DefaultImages
import com.ayush.plantwateringreminder.feature.plantfeature.Domain.Model.DefaultImagesX
import com.ayush.plantwateringreminder.feature.plantfeature.Domain.Model.Plant
import com.ayush.plantwateringreminder.feature.plantfeature.Domain.Model.PlantDetails
import com.ayush.plantwateringreminder.feature.plantfeature.Domain.Model.WateringGeneralBenchmarks
fun PlantDto.toDomain(): Plant {
    return Plant(
        common_name = this.common_name,
        default_image = this.default_image?.toDomain()?:DefaultImages("Unknown"),
        watering = this.watering?: "Unknown",
        id = this.id,
    )
}

fun DefaultImage.toDomain(): DefaultImages {
    return DefaultImages(
        thumbnail = this.thumbnail?:"Unknown"
    )
}


fun PlantDtoDetail.toDomain(): PlantDetails {
    return PlantDetails(
        common_name = this.common_name,
        description = this.description,
        id = this.id,
        soil = this.soil,
        volume_water_requirement = this.volume_water_requirement,
        watering = this.watering,
        watering_general_benchmark = this.watering_general_benchmark.toDomain(),
        watering_period = this.watering_period,
        default_image = this.default_image?.toDomain()?:DefaultImagesX("Unknown")
    )
}
fun DefaultImageX.toDomain(): DefaultImagesX{
    return DefaultImagesX(
        original_url = this.original_url?:"Unknown"
    )
}
fun WateringGeneralBenchmark.toDomain(): WateringGeneralBenchmarks {
    return WateringGeneralBenchmarks(
        unit = this.unit,
        value = this.value
    )
}
