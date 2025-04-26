package com.kurly.assignment.main.viewmodel.state

import com.kurly.assignment.domain.main.model.Product
import com.kurly.assignment.domain.main.model.Section

data class MainUiState(
    val sections: List<Section> = emptyList(),
    val productMap: Map<Int, List<Product>> = emptyMap(),
    val wishlist: Set<Int> = emptySet(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val error: String? = null
)