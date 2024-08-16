package com.ayush.plantwateringreminder.feature.plantfeature.Presentation.util

sealed class Screens(val route:String) {
    object PlantScreen : Screens("plant_screen")
    object PlantDetailScreen : Screens("plant_detail_screen")

}