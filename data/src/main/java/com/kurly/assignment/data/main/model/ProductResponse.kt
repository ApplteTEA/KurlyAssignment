package com.kurly.assignment.data.main.model

data class ProductResponse(
    val data: List<ProductDto>
)

data class ProductDto(
    val id: Int,
    val name: String,
    val image: String,
    val originalPrice: Int,
    val discountedPrice: Int? = null,
    val isSoldOut: Boolean
)