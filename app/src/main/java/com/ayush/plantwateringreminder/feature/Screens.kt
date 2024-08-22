package com.ayush.plantwateringreminder.feature

sealed class Screens(val route:String) {
    data object PlantScreen : Screens("plant_screen")
    data object PlantDetailScreen : Screens("plant_detail_screen")

    data object LikePlant:Screens("like_plant")
    data object LikePlantDetail:Screens("like_plant_detail")

    // navGraph

    data object PlantApi: Screens("plant_api")

    data object PlantDatabase: Screens("plant_database")

}