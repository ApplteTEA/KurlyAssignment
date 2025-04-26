package com.kurly.assignment.main.view.component

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kurly.assignment.domain.main.model.Product
import com.kurly.assignment.ext.WidthMargin

@Composable
fun HorizontalProductList(
    products: List<Product>,
    wishlist: Set<Int>,
    onToggleWish: (Int) -> Unit
) {
    val scrollState = rememberScrollState()
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .horizontalScroll(scrollState)
            .height(intrinsicSize = IntrinsicSize.Max)
    ) {
        repeat(products.size) {
            if (it == 0) {
                4.WidthMargin()
            }

            val product = products[it]
            ProductItem(
                product = product,
                isWished = wishlist.contains(product.id),
                onToggleWish = { onToggleWish(product.id) },
                modifier = Modifier.width(150.dp)
            )

            if (it == products.size - 1) {
                4.WidthMargin()
            }
        }
    }
}