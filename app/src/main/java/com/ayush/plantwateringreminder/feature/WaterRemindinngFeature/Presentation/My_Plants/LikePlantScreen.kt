package com.ayush.plantwateringreminder.feature.WaterRemindinngFeature.Presentation.My_Plants

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ayush.plantwateringreminder.R
import com.ayush.plantwateringreminder.feature.WaterRemindinngFeature.Presentation.My_Plants.component.BottomNavigationItem
import com.ayush.plantwateringreminder.feature.WaterRemindinngFeature.Presentation.My_Plants.component.LikePlantItem
import com.ayush.ui.theme.White


@SuppressLint("SuspiciousIndentation")
@Composable
fun LikePlantScreen(
    viewModel: LikePlantViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = viewModel.state
    val plantLogo = painterResource(id = R.drawable.logo)


       Box(modifier = Modifier.fillMaxSize()){
        Column {
            Image(
                painter = plantLogo, contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 5.dp)
                    .size(200.dp)
            )
            Spacer(modifier = Modifier.height(7.dp))

            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(state.myPlants) { plant ->
                    LikePlantItem(
                        plant = plant,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 10.dp, end = 10.dp, top = 10.dp
                            )
                            .width(300.dp)
                            .background(White)
                            .height(IntrinsicSize.Min)
                            .clickable {

                            }
                    )
                }
            }
        }
        }
    }

