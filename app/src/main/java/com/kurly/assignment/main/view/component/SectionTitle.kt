package com.kurly.assignment.main.view.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.kurly.assignment.ext.color
import com.kurly.assignment.ext.dp2sp

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        fontWeight = FontWeight.Bold,
        fontSize = dp2sp(dp = 16.dp),
        color = "#222222".color,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
        modifier = Modifier.padding(16.dp)
    )
}