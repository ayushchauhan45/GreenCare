package com.ayush.plantwateringreminder.feature.plantfeature.Presentation.Plant_details

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PlantDetailsScreen(
    navController: NavController,
    id:Int?,
    viewModel: PlantDetailViewModel = hiltViewModel()
) {
    val snackBarState =  remember{SnackbarHostState()}

    LaunchedEffect(key1 = true) {
       viewModel.eventFlow.collectLatest {event->
           when(event){
               PlantDetailViewModel.UiEvent.LikePlant -> TODO()
               is PlantDetailViewModel.UiEvent.ShowSnackBar -> {
                 snackBarState.showSnackbar(
                     message = event.message,
                     duration = SnackbarDuration.Long
                 )
               }
           }
       }
    }

    val state = viewModel.state
    if (id == null) {
        Text(text = "Plant Id is null")
    } else {
        if (state.error == null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                state.plantDetail?.let { plantDetail ->
                    PlantDetailsItem(plantDetails = plantDetail,
                        onBackClick = { navController.popBackStack() },
                        onEvent = {viewModel.onEvent(PlantDetailsEvent.LikePlant)}
                    )
                }
            }
        }else if (state.isLoading){
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }

        }
    }
}
