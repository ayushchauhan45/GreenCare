package com.ayush.plantwateringreminder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ayush.plantwateringreminder.feature.plantfeature.Presentation.Plant_details.PlantDetailsScreen
import com.ayush.plantwateringreminder.feature.plantfeature.Presentation.Plant_list.PlantScreen
import com.ayush.plantwateringreminder.feature.Screens
import com.ayush.plantwateringreminder.feature.WaterRemindinngFeature.Presentation.My_Plants.LikePlantScreen
import com.ayush.ui.theme.PlantWateringReminderTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlantWateringReminderTheme {
              val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screens.PlantDatabase.route
                ) {
                    navigation(
                        route = Screens.PlantDatabase.route,
                        startDestination = Screens.LikePlant.route
                    ){
                        composable(Screens.LikePlant.route){
                            LikePlantScreen(navController = navController)
                        }
                        composable(Screens.LikePlantDetail.route){

                        }
                    }
                    navigation(
                        route = Screens.PlantApi.route,
                        startDestination = Screens.PlantScreen.route
                    ) {
                    composable(Screens.PlantScreen.route) {
                        PlantScreen(
                            navController = navController
                        )
                    }
                    composable(Screens.PlantDetailScreen.route + "?id={id}",
                        arguments = listOf(
                            navArgument(name = "id") {
                                type = NavType.IntType
                            }
                        )
                    ) { backStackEntry ->
                        val id = backStackEntry.arguments?.getInt("id")
                        PlantDetailsScreen(
                            navController = navController,
                            id = id
                        )
                    }
                }
                }
            }
        }
    }
}

