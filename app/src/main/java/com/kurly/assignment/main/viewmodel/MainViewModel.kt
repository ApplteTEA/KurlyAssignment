package com.kurly.assignment.main.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kurly.assignment.datastore.WishlistStorage
import com.kurly.assignment.domain.main.model.Product
import com.kurly.assignment.domain.main.model.Section
import com.kurly.assignment.domain.main.usecase.FetchMainProductsUseCase
import com.kurly.assignment.domain.main.usecase.FetchMainSectionsUseCase
import com.kurly.assignment.main.viewmodel.state.MainUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext private val appContext: Context,
    private val fetchSectionsUseCase: FetchMainSectionsUseCase,
    private val fetchProductsUseCase: FetchMainProductsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState

    private var nextPage: Int? = 1

    init {
        //### 앱 시작 시 wishlist 로딩
        loadWishlist()
    }

    /**
     * Section Data 로드
     * ---------------------------------------------------------------------------------------------
     */
    fun loadSections() {
        if (nextPage == null || _uiState.value.isLoading) return

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            try {
                val (sections, productMap) = fetchAndMapSections(nextPage!!)

                _uiState.update {
                    it.copy(
                        sections = it.sections + sections,
                        productMap = _uiState.value.productMap + productMap,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _uiState.update {
                    it.copy(isLoading = false, error = e.message ?: "알 수 없는 오류")
                }
            }
        }
    }

    /**
     * 좋아요 리스트 변경
     * ---------------------------------------------------------------------------------------------
     */
    fun toggleWishlist(productId: Int) {
        val newWishlist = _uiState.value.wishlist.toMutableSet().apply {
            if (contains(productId)) remove(productId) else add(productId)
        }

        _uiState.update { it.copy(wishlist = newWishlist) }

        viewModelScope.launch {
            WishlistStorage.saveWishlist(appContext, newWishlist)
        }
    }

    /**
     * 당겨서 새로고침
     * ---------------------------------------------------------------------------------------------
     */
    fun refreshSection() {
        if (_uiState.value.isRefreshing) return

        viewModelScope.launch {
            _uiState.update { it.copy(isRefreshing = true, error = null) }
            nextPage = 1

            try {
                val (sections, productMap) = fetchAndMapSections(nextPage!!)

                _uiState.update {
                    it.copy(
                        sections = sections,
                        productMap = productMap,
                        isRefreshing = false
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(isRefreshing = false, error = e.message ?: "알 수 없는 오류")
                }
            }
        }
    }


    //##############################################################################################
    //##
    //## >> Custom Method
    //##
    //##############################################################################################

    /**
     * Section Data 조회하기
     * ---------------------------------------------------------------------------------------------
     */
    private suspend fun fetchAndMapSections(page: Int): Pair<List<Section>, Map<Int, List<Product>>> {
        val (sections, newNextPage) = fetchSectionsUseCase(page)
        nextPage = newNextPage

        val productMap = coroutineScope {
            sections.map { section ->
                async { section.id to fetchProductsUseCase(section.id) }
            }.awaitAll().toMap()
        }

        return sections to productMap
    }

    /**
     * 좋아요 리스트 불러오기
     * ---------------------------------------------------------------------------------------------
     */
    private fun loadWishlist() {
        viewModelScope.launch {
            val savedWishlist = WishlistStorage.loadWishlist(appContext)
            _uiState.update { it.copy(wishlist = savedWishlist) }
        }
    }
}