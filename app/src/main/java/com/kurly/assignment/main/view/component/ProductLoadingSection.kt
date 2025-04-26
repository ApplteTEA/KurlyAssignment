package com.kurly.assignment.main.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kurly.assignment.ext.color
import com.kurly.assignment.ext.HeightMargin
import com.kurly.assignment.ext.shimmerEffect
import com.kurly.assignment.ext.WidthMargin

@Composable
fun ProductLoadingSection() {
    ShimmerTitle()

    val scrollState = rememberScrollState()
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .horizontalScroll(scrollState)
            .height(intrinsicSize = IntrinsicSize.Max)
    ) {
        repeat(5) {
            if (it == 0) {
                4.WidthMargin()
            }

            ShimmerProductItem(modifier = Modifier.width(150.dp))

            if (it == 4) {
                4.WidthMargin()
            }
        }
    }

    16.HeightMargin()
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background("#d5d5d5".color)
    )

    ShimmerTitle()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        repeat(2) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                repeat(3) {
                    ShimmerProductItem(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
fun ShimmerTitle() {
    Box(modifier = Modifier.padding(16.dp)) {
        Box(
            modifier = Modifier
                .height(16.dp)
                .fillMaxWidth(0.2f)
                .shimmerEffect()
        )
    }
}