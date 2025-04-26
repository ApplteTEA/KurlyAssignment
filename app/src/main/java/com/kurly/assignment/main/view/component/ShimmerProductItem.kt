package com.kurly.assignment.main.view.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.kurly.assignment.ext.HeightMargin
import com.kurly.assignment.ext.shimmerEffect

@Composable
fun ShimmerProductItem(
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .aspectRatio(150f / 200f)
                .clip(RoundedCornerShape(12.dp))
                .shimmerEffect()
        )

        8.HeightMargin()

        Box(
            modifier = Modifier
                .height(16.dp)
                .fillMaxWidth()
                .shimmerEffect()
        )

        4.HeightMargin()

        Box(
            modifier = Modifier
                .height(16.dp)
                .fillMaxWidth(0.6f)
                .shimmerEffect()
        )
    }
}