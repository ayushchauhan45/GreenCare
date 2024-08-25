package com.ayush.plantwateringreminder.feature.PlantDatabase.Presentation.My_Plants.component

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItem(
    val title:String,
    val selected: ImageVector,
    val unselected: ImageVector,
    val navigationRoute: String
)
