package com.ayush.plantwateringreminder.feature.plantfeature.Presentation.Plant_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ayush.plantwateringreminder.R
import com.ayush.plantwateringreminder.feature.plantfeature.Domain.Model.Plant

@Composable
fun PlantItem(
    plant:Plant,
    modifier: Modifier = Modifier,
){
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
          Row {
              Box(
                  modifier = Modifier
                      .weight(1.3f)
                      .background(Color(0xFFC9D8CC))
                      .fillMaxHeight()
              ) {
                AsyncImage(model = plant.default_image.thumbnail, contentDescription = null,
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(10.dp))
                )
              }
              Box(
                  modifier = Modifier
                      .weight(2f)
                      .background(Color(0xFFDAF3BF)) // White background for the text section
                      .padding(10.dp)
                      .fillMaxHeight()
              ){
                  Column {
                      Text(text = plant.common_name,
                          color = Color(0xFF024405),
                          fontSize = 16.sp,
                          overflow = TextOverflow.Ellipsis,
                          maxLines = 1,
                          modifier = Modifier.align(Alignment.Start)
                      )
                      Spacer(modifier = Modifier.height(20.dp))
                     Row {
                         Image(painter = painterResource(id = R.drawable.watering_can),
                             contentDescription = "watering Can")
                         Spacer(modifier = Modifier.width(5.dp))
                         Text(text = plant.watering,
                             color = Color(0xFF024405),
                             fontSize = 14.sp
                         )


                     }

                  }


              }
          }


    }

}