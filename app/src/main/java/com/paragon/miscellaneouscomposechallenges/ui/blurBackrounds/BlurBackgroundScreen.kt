package com.paragon.miscellaneouscomposechallenges.ui.blurBackrounds

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.paragon.miscellaneouscomposechallneges.R
import com.skydoves.cloudy.cloudy

@Composable
fun BlurBackgroundScreen() {
    val list = listOf(
        R.mipmap.car0,
        R.mipmap.car1,
        R.mipmap.car2,
        R.mipmap.car3,
        R.mipmap.car4,
        R.mipmap.car5,
        R.mipmap.car6,
        R.mipmap.car7,
        R.mipmap.car8,
        R.mipmap.car9,
        R.mipmap.car10
    )
    Box(modifier = Modifier.fillMaxSize())
    BlurBackground(list = list)
    BlurBackgroundShapes()
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BlurBackgroundShapes() {
    FlowRow(
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally)
    ) {
        repeat(100) {
            Box(
                modifier = Modifier
                    .padding(5.dp)
                    .size(100.dp)
                    .cloudy(100)
                    .clip(RoundedCornerShape(50))
            ) {
                Spacer(
                    modifier = Modifier
                        .matchParentSize()
                        .cloudy(6)
                        .background(Color.Black.copy(0.5f))
                        .clip(RoundedCornerShape(50))
                        .cloudy(100)
                )
            }
        }
    }
}

@Composable
fun BlurBackground(list: List<Int>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn {
            itemsIndexed(list) { _, item ->
                Image(
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    painter = painterResource(id = item),
                    contentDescription = ""
                )
            }
        }
    }
}
