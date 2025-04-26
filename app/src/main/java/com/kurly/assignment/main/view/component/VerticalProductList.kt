package com.kurly.assignment.main.view.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kurly.assignment.domain.main.model.Product

@Composable
fun VerticalProductList(
    products: List<Product>,
    wishlist: Set<Int>,
    onToggleWish: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        products.forEach { product ->
            ProductExpandItem(
                product = product,
                isWished = wishlist.contains(product.id),
                onToggleWish = { onToggleWish(product.id) }
            )
        }
    }
}