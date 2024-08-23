package com.ayush.plantwateringreminder.feature.plantfeature.Presentation.Plant_details

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ayush.plantwateringreminder.feature.Screens
import com.ayush.plantwateringreminder.feature.plantfeature.Presentation.Plant_details.Component.FavouriteIconState
import com.ayush.plantwateringreminder.feature.plantfeature.Presentation.Plant_details.Component.PlantDetailsEvent
import com.ayush.plantwateringreminder.feature.plantfeature.Presentation.Plant_details.Component.PlantDetailsItem
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PlantDetailsScreen(
    navController: NavController,
    id:Int?,
    viewModel: PlantDetailViewModel = hiltViewModel()
) {
    val snackBarState =  remember{SnackbarHostState()}

    var isLiked by rememberSaveable { mutableStateOf(false) }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    LaunchedEffect(key1 = true) {
       viewModel.eventFlow.collectLatest {event->
           when(event){
               PlantDetailViewModel.UiEvent.LikePlant -> {
                   navController.navigate(Screens.LikePlant.route){
                       popUpTo(Screens.PlantApi.route){
                           inclusive = true
                       }
                   }
               }
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

    Scaffold (

        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(title = { Text(text = "Plant Details") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                    }
                },
                actions = {
                    IconButton(onClick = {
                        isLiked = !isLiked
                   if (isLiked){
                      viewModel.onEvent(PlantDetailsEvent.LikePlant)
                    }else{
                     viewModel.onEvent(PlantDetailsEvent.UnLikePlant)
                   }
                    }
                    ) {
                  Icon(imageVector = if(isLiked)Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder, contentDescription = null)
                    }
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) {padding->
            if (state.error == null) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .verticalScroll(rememberScrollState())
                ) {
                    state.plantDetail?.let { plantDetail ->
                        PlantDetailsItem(plantDetails = plantDetail)
                    }
                }
            } else if (state.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }

            }
    }
}
