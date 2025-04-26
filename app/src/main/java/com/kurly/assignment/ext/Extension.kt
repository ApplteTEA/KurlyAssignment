package com.kurly.assignment.ext

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.imageLoader
import coil.request.ImageRequest
import coil.size.Size
import com.kurly.assignment.domain.main.model.Product
import java.text.DecimalFormat

/**
 * Hex Code -> Color
 * ---------------------------------------------------------------------------------------------
 */
val String?.color get() = Color(android.graphics.Color.parseColor(
    if (this.isNullOrEmpty()) "#ffffff"
    else this
))

/**
 * Dp to Sp
 * ---------------------------------------------------------------------------------------------
 */
@Composable
fun dp2sp(dp: Dp) = with(LocalDensity.current) { dp.toSp() }

/**
 * Modifier Click Action
 * ---------------------------------------------------------------------------------------------
 */
@SuppressLint("ModifierFactoryUnreferencedReceiver")
inline fun Modifier.noRippleClickable(crossinline onClick: ()->Unit): Modifier = composed {
    clickable(indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}

/**
 * Width Margin
 * ---------------------------------------------------------------------------------------------
 */
@Composable
fun Int.WidthMargin() = Spacer(modifier = Modifier.width(this.dp))

/**
 * Height Margin
 * ---------------------------------------------------------------------------------------------
 */
@Composable
fun Int.HeightMargin() = Spacer(modifier = Modifier.height(this.dp))

/**
 * xml Image Change
 * ---------------------------------------------------------------------------------------------
 */
@Composable
fun Int.DrawDrawableImage(modifier: Modifier = Modifier, contentScale: ContentScale = ContentScale.FillBounds) {
    Image(
        painter = painterResource(id = this),
        contentDescription = null,
        modifier = modifier,
        contentScale = contentScale
    )
}

/**
 * URL Image Change
 * ---------------------------------------------------------------------------------------------
 */
@Composable
fun String.DrawUrlImage(modifier: Modifier = Modifier, contentScale: ContentScale = ContentScale.FillBounds) {
    if (this.isEmpty()) return

    val context = LocalContext.current

    val imageRequest = ImageRequest.Builder(context)
        .data(this)
        .size(Size.ORIGINAL)
        .build()

    AsyncImage(
        model = imageRequest,
        contentDescription = null,
        imageLoader = LocalContext.current.imageLoader,
        contentScale = if (this.contains(".gif")) ContentScale.Crop else contentScale,
        modifier = modifier
    )
}

/**
 * 스켈레톤 로딩 화면 이펙트
 * ---------------------------------------------------------------------------------------------
 */
fun Modifier.shimmerEffect(): Modifier = composed {
    var size by remember { mutableStateOf(IntSize.Zero) }
    val transition = rememberInfiniteTransition(label = "shimmer")
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(animation = tween(1000)),
        label = "offset"
    )

    background(
        brush = Brush.linearGradient(
            colors = listOf(
                Color(0xFFB8B5B5),
                Color(0xFF8F8B8B),
                Color(0xFFB8B5B5),
            ),
            start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
        )
    ).onGloballyPositioned {
        size = it.size
    }
}

/**
 * 할인율 계산
 * ---------------------------------------------------------------------------------------------
 */
fun Product.discountPercent(): Int {
    val sale = discountedPrice ?: return 0
    val origin = originalPrice
    return ((1 - (sale.toDouble() / origin)) * 100).toInt()
}

private val decimal = DecimalFormat("#,###")

/**
 * 숫자 천단위 콤마
 * ---------------------------------------------------------------------------------------------
 */
fun Int?.decimalComma(): String {
    return try {
        decimal.format(this)
    } catch (_: Exception) {
        "0"
    }
}