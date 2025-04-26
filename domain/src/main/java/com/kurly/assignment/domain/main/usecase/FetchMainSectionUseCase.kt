package com.kurly.assignment.domain.main.usecase

import com.kurly.assignment.domain.main.model.Section
import com.kurly.assignment.domain.main.repository.MainRepository
import javax.inject.Inject

class FetchMainSectionsUseCase @Inject constructor(private val repository: MainRepository) {

    suspend operator fun invoke(page: Int): Pair<List<Section>, Int?> {
        return repository.getSections(page)
    }
}