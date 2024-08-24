package com.ayush.plantwateringreminder.feature.WaterRemindinngFeature.Presentation.My_Plants_Details

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ayush.plantwateringreminder.feature.WaterRemindinngFeature.Presentation.My_Plants_Details.Component.LikePlantDetailsItem
import com.ayush.plantwateringreminder.feature.plantfeature.Presentation.Plant_details.Component.PlantDetailsEvent
import com.ayush.plantwateringreminder.feature.plantfeature.Presentation.Plant_details.Component.PlantDetailsItem


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LikePlantDetailsScreen(
    navController: NavController,
    id:Int?,
    viewModel: LikePlantDetailViewModel = hiltViewModel()
) {
    val snackBarState =  remember{ SnackbarHostState() }

    var isLiked by rememberSaveable { mutableStateOf(false) }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()


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
                    }
                    ) {
                        Icon(imageVector = if(isLiked) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder, contentDescription = null)
                    }
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) {padding->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
            ) {
                state.myPlant?.let { plantEntity ->
                    LikePlantDetailsItem(plantEntity = plantEntity)
                }
            }
        }

}

