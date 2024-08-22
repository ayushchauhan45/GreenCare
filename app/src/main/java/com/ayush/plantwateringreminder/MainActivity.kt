package com.ayush.plantwateringreminder

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
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
import com.ayush.plantwateringreminder.feature.WaterRemindinngFeature.Presentation.My_Plants.component.BottomNavigationItem
import com.ayush.ui.theme.PlantWateringReminderTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlantWateringReminderTheme {

        }
    }
}
@Composable
fun MainScreen(navController: NavHostController = rememberNavController()) {
    val bottomItem = listOf(
        BottomNavigationItem(
            title = "Home",
            selected = Icons.Filled.Favorite,
            unselected = Icons.Outlined.Favorite,
            navigationRoute = Screens.LikePlant.route
        ),
        BottomNavigationItem(
            title = "Search",
            selected = Icons.Filled.Search,
            unselected = Icons.Outlined.Search,
            navigationRoute = Screens.PlantScreen.route
        )
    )

    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                bottomItem.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = {
                            selectedIndex = index
                            navController.navigate(item.navigationRoute)
                        },
                        icon = {
                            Icon(
                                imageVector = if (index == selectedIndex) item.selected else item.unselected,
                                contentDescription = item.title
                            )
                        }
                    )
                }
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Screens.PlantDatabase.route,
            modifier = Modifier.padding(padding)
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

