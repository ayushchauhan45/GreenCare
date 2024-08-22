package com.ayush.plantwateringreminder.feature.WaterRemindinngFeature.Presentation.My_Plants.component

import androidx.compose.ui.graphics.vector.ImageVector
import com.ayush.plantwateringreminder.feature.Screens

data class BottomNavigationItem(
    val title:String,
    val selected: ImageVector,
    val unselected: ImageVector,
    val navigationRoute: String
)
