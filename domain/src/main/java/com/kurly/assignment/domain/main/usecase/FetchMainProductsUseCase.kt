package com.kurly.assignment.domain.main.usecase

import com.kurly.assignment.domain.main.model.Product
import com.kurly.assignment.domain.main.repository.MainRepository
import javax.inject.Inject

class FetchMainProductsUseCase @Inject constructor(private val repository: MainRepository) {

    suspend operator fun invoke(sectionId: Int): List<Product> {
        return repository.getProducts(sectionId)
    }
}