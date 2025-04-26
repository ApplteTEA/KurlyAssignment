package com.kurly.assignment.data.main.mapper

import com.kurly.assignment.data.main.model.ProductDto
import com.kurly.assignment.domain.main.model.Product
import javax.inject.Inject

class ProductMapper @Inject constructor() {
    fun mapToDomain(dto: ProductDto): Product = Product(
        id = dto.id,
        name = dto.name,
        imageUrl = dto.image,
        originalPrice = dto.originalPrice,
        discountedPrice = dto.discountedPrice,
        isSoldOut = dto.isSoldOut
    )
}