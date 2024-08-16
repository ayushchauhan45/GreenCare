package com.ayush.plantwateringreminder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.navigation.NavHost
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ayush.plantwateringreminder.feature.plantfeature.Presentation.Plant_details.PlantDetailsScreen
import com.ayush.plantwateringreminder.feature.plantfeature.Presentation.Plant_list.PlantScreen
import com.ayush.plantwateringreminder.feature.plantfeature.Presentation.util.Screens
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
                    startDestination = "plant_screen"
                ){
                    composable("plant_screen"){
                        PlantScreen(
                            navController = navController
                        )

                    }
                    composable( "plant_detail_screen/{id}",
                        arguments = listOf(
                            navArgument("id"){
                                type = NavType.IntType
                            }
                        )
                    ){backStackEntry->
                      val id  = backStackEntry.arguments?.getInt("id")!!
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

