package com.kurly.assignment.main.view.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kurly.assignment.domain.main.model.Product

@Composable
fun GridProductList(
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
        products.chunked(3).forEach { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                rowItems.forEach { product ->
                    ProductItem(
                        product = product,
                        isWished = wishlist.contains(product.id),
                        onToggleWish = { onToggleWish(product.id) },
                        modifier = Modifier.weight(1f)
                    )
                }

                repeat(3 - rowItems.size) {
                    Box(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}