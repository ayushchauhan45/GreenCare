package com.ayush.plantwateringreminder.feature.plantfeature.Presentation.Plant_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.sharp.DateRange
import androidx.compose.material.icons.sharp.FavoriteBorder
import androidx.compose.material.icons.sharp.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.ayush.plantwateringreminder.R
import com.ayush.plantwateringreminder.feature.plantfeature.Domain.Model.PlantDetails

@Composable
fun PlantDetailsItem(plantDetails: PlantDetails,
                     onBackClick:()->Unit)
{
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 25.dp,
                    start = 15.dp,
                    end = 15.dp
                )
            ,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier
                .size(50.dp)
                .shadow(8.dp, CircleShape)
                .clip(CircleShape)
                .background(Color.White)
            ){
                IconButton(onClick = {
                    onBackClick()
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back Button"
                    )
                }
            }
            Text(
                text = "Details",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Box(modifier = Modifier
                .size(50.dp)
                .shadow(4.dp, CircleShape)
                .clip(CircleShape)
                .background(Color.White)
            ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Sharp.FavoriteBorder,
                        contentDescription = "Favourite"
                    )
                }

            }

        }
        Spacer(modifier = Modifier.height(25.dp))
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 10.dp,
                end = 10.dp
            )
            .clip(RoundedCornerShape(8.dp))
            .shadow(5.dp, RoundedCornerShape(5.dp))
        ){
            SubcomposeAsyncImage(model = plantDetails.default_image.original_url,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                loading = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            ,
                        contentAlignment = Alignment.Center
                    ){
                        CircularProgressIndicator()
                    }
                },
                error = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Gray.copy(alpha = 0.3f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id =R.drawable.baseline_broken_image_24),
                            contentDescription = "Image not available",
                            tint = Color.Gray
                        )
                    }
                }
            )


        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(text ="#${plantDetails.id} ${plantDetails.common_name}",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 5.dp))
        Spacer(modifier = Modifier.height(5.dp))
        ExpandableText(text = plantDetails.description,
            modifier = Modifier.padding(start = 5.dp, end = 5.dp))
        Spacer(modifier = Modifier.height(5.dp))

        Text(text = "Care Info" ,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(start = 5.dp))
        Spacer(modifier = Modifier.height(15.dp))
        Row {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .shadow(3.dp, RoundedCornerShape(3.dp))
                    .background(
                        Color(
                            0xFFEBF1FA
                        )
                    )
                    .padding(start = 8.dp)
            ) {
                Icon(imageVector = Icons.Sharp.DateRange, contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(30.dp))
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(text = "Watering Period",
                    fontWeight = FontWeight.Bold)
                Text(text = plantDetails.watering_period.toString(),
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(0xFF038530
                    ))
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .shadow(3.dp, RoundedCornerShape(3.dp))
                    .background(
                        Color(
                            0xFFEBF1FA
                        )
                    )
                    .padding(start = 8.dp)
            ) {
                Icon(painter = painterResource(id = R.drawable.baseline_water_drop_24), contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(30.dp))
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(text = "Watering Volume",
                    fontWeight = FontWeight.Bold)

                val WaterVolumeText = plantDetails.volume_water_requirement.joinToString(", ") { watervolume ->
                    watervolume.toString()
                }

                Text(text = WaterVolumeText ,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color(0xFF038530
                        ))
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .shadow(3.dp, RoundedCornerShape(3.dp))
                    .background(
                        Color(
                            0xFFEBF1FA
                        )
                    )
                    .padding(start = 8.dp)
            ) {
                Icon(painter = painterResource(id = R.drawable.baseline_watch_later_24), contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(30.dp))
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(text = "Watering Benchmark",
                    fontWeight = FontWeight.Bold)
                Text(text = plantDetails.watering,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(0xFF038530
                    ))
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .shadow(3.dp, RoundedCornerShape(3.dp))
                    .background(
                        Color(
                            0xFFEBF1FA
                        )
                    )
                    .padding(start = 8.dp)
            ) {
                Icon(painter = painterResource(id = R.drawable.watering_can) , contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(30.dp))
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(text = "Watering BenchMark",
                    fontWeight = FontWeight.Bold)
                    Text(text =" ${plantDetails.watering_general_benchmark.value} ${plantDetails.watering_general_benchmark.unit} ",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color(0xFF038530
                        ),
                        maxLines = 1)



            }
        }
        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Soil",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 5.dp))
        Spacer(modifier = Modifier.height(10.dp))
        val soilTypesText = plantDetails.soil.joinToString(", ") { soilType ->
            soilType.toString()
        }

            Text(text = "${plantDetails.common_name} plant requires  $soilTypesText soils"  ,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 10.dp,
                        bottom = 25.dp
                    ),
            )




    }

 }