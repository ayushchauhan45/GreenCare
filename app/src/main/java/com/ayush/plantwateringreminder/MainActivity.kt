package com.ayush.plantwateringreminder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ayush.plantwateringreminder.feature.plantfeature.Presentation.Plant_list.PlantScreen
import com.ayush.ui.theme.PlantWateringReminderTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlantWateringReminderTheme {
                     PlantScreen()
            }
        }
    }
}

