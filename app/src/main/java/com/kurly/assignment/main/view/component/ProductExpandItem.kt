package com.kurly.assignment.main.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.kurly.assignment.R
import com.kurly.assignment.domain.main.model.Product
import com.kurly.assignment.ext.DrawDrawableImage
import com.kurly.assignment.ext.DrawUrlImage
import com.kurly.assignment.ext.color
import com.kurly.assignment.ext.decimalComma
import com.kurly.assignment.ext.discountPercent
import com.kurly.assignment.ext.dp2sp
import com.kurly.assignment.ext.HeightMargin
import com.kurly.assignment.ext.noRippleClickable

@Composable
fun ProductExpandItem(
    product: Product,
    isWished: Boolean,
    onToggleWish: () -> Unit,
) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(6f / 4f)
                .clip(RoundedCornerShape(12.dp))
        ) {
            product.imageUrl.DrawUrlImage(
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
                    .noRippleClickable { onToggleWish() }
            ) {
                val likeImage =
                    if (isWished) R.drawable.ic_btn_heart_on else R.drawable.ic_btn_heart_off
                likeImage.DrawDrawableImage()
            }

            if (product.isSoldOut) {
                ProductSoldOut()
            }
        }

        8.HeightMargin()

        Text(
            text = product.name,
            fontSize = dp2sp(dp = 15.dp),
            color = "#222222".color,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        4.HeightMargin()

        if (product.discountedPrice != null) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "${product.discountPercent()}%",
                    fontSize = dp2sp(dp = 15.dp),
                    color = "#fa622f".color,
                )
                Text(
                    text = "${product.discountedPrice.decimalComma()}원",
                    fontWeight = FontWeight.Bold,
                    fontSize = dp2sp(dp = 15.dp),
                    color = "#222222".color
                )
                Text(
                    text = "${product.originalPrice.decimalComma()}원",
                    fontSize = dp2sp(dp = 13.dp),
                    color = "#222222".color,
                    textDecoration = TextDecoration.LineThrough
                )
            }
        } else {
            Text(
                text = "${product.originalPrice.decimalComma()}원",
                fontWeight = FontWeight.Bold,
                fontSize = dp2sp(dp = 15.dp),
                color = "#222222".color
            )
        }
    }
}
