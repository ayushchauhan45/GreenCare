package com.ayush.plantwateringreminder.feature.plantfeature.Presentation.Plant_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun PlantDetailsScreen(
    navController: NavController,
    id:Int,
    viewModel: PlantDetailViewModel = hiltViewModel()
){
    val state = viewModel.state

    if(state.error==null){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
          state.plantDetail?.let { plantDetail->
              PlantDetailsItem(plantDetails = plantDetail,
                  onBackClick = {navController.popBackStack()}
                  )
          }
        }
    }
}