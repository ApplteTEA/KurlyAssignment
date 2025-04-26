package com.kurly.assignment.main.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kurly.assignment.R
import com.kurly.assignment.ext.color
import com.kurly.assignment.ext.dp2sp
import com.kurly.assignment.ext.noRippleClickable

@Composable
fun ProductSoldOut() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background("#000000".color.copy(alpha = 0.8f))
            .noRippleClickable{}
    ) {
        Text(
            text = stringResource(id = R.string.product_sold_out),
            fontSize = dp2sp(dp = 13.dp),
            color = "#ffffff".color,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}