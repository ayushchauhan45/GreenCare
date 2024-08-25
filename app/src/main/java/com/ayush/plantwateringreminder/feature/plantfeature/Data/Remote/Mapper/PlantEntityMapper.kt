package com.ayush.plantwateringreminder.feature.plantfeature.Data.Remote.Mapper

import com.ayush.plantwateringreminder.feature.PlantDatabase.Domain.Model.PlantEntity
import com.ayush.plantwateringreminder.feature.plantfeature.Domain.Model.PlantDetails

fun PlantDetails.toEntity(): PlantEntity {
    return PlantEntity(
        id = this.id,
        commonName = this.common_name,
        description = this.description,
        thumbnailImageUrl = this.default_image.thumbnail,
        watering = this.watering,
        wateringUnit = this.watering_general_benchmark.unit,
        wateringValue = this.watering_general_benchmark.value,
        originalImageUrl = this.default_image.original_url,
        wateringPeriod = this.watering_period.toString()
    )
}

