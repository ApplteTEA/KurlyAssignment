package com.kurly.assignment.domain.main.model

data class Product(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val originalPrice: Int,
    val discountedPrice: Int?,
    val isSoldOut: Boolean
)