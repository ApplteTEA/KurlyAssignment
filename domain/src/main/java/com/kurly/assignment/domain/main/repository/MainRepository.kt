package com.kurly.assignment.domain.main.repository

import com.kurly.assignment.domain.main.model.Product
import com.kurly.assignment.domain.main.model.Section

interface MainRepository {

    suspend fun getSections(page: Int): Pair<List<Section>, Int?>
    suspend fun getProducts(sectionId: Int): List<Product>
}