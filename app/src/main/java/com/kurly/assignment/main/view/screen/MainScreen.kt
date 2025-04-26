package com.kurly.assignment.main.view.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kurly.assignment.R
import com.kurly.assignment.domain.main.model.Product
import com.kurly.assignment.ext.color
import com.kurly.assignment.ext.dp2sp
import com.kurly.assignment.ext.HeightMargin
import com.kurly.assignment.main.view.component.GridProductList
import com.kurly.assignment.main.view.component.HorizontalProductList
import com.kurly.assignment.main.view.component.ProductLoadingSection
import com.kurly.assignment.main.view.component.SectionTitle
import com.kurly.assignment.main.view.component.VerticalProductList
import com.kurly.assignment.main.viewmodel.MainViewModel
import kotlinx.coroutines.flow.distinctUntilChanged

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(viewModel: MainViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val lazyListState = rememberLazyListState()

    //### 당겨서 새로고침 로직
    val pullRefreshState = rememberPullRefreshState(
        refreshing = uiState.isRefreshing,
        onRefresh = { viewModel.refreshSection() }
    )

    //### 최초 진입 시 데이터 로딩
    LaunchedEffect(Unit) {
        viewModel.loadSections()
    }

    //### 페이징 처리
    LaunchedEffect(lazyListState) {
        snapshotFlow { lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .distinctUntilChanged()
            .collect { lastVisibleItemIndex ->
                if (lastVisibleItemIndex == uiState.sections.lastIndex) {
                    viewModel.loadSections()
                }
            }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .then(
                if (!uiState.isLoading) Modifier.pullRefresh(pullRefreshState)
                else Modifier
            )
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = lazyListState
        ) {
            when {
                //# 최초 진입 전체 로딩
                uiState.sections.isEmpty() && uiState.isLoading && !uiState.isRefreshing -> {
                    item {
                        ProductLoadingSection()
                    }
                }
                //# Section Data가 비어있다면
                uiState.sections.isEmpty() -> {
                    item {
                        Box(
                            modifier = Modifier.fillParentMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = stringResource(R.string.section_product_empty),
                                fontWeight = FontWeight.Bold,
                                fontSize = dp2sp(dp = 18.dp),
                                color = "#222222".color
                            )
                        }
                    }
                }
                //# 최초진입도 아니고, Section Data도 존재한다면
                else -> {
                    items(uiState.sections.size) { idx ->
                        val section = uiState.sections[idx]
                        SectionTitle(title = section.title)

                        val products: List<Product> = uiState.productMap[section.id] ?: emptyList()

                        when (section.type) {
                            "horizontal" -> HorizontalProductList(
                                products = products,
                                wishlist = uiState.wishlist,
                                onToggleWish = viewModel::toggleWishlist
                            )
                            "grid" -> GridProductList(
                                products = products,
                                wishlist = uiState.wishlist,
                                onToggleWish = viewModel::toggleWishlist
                            )
                            "vertical" -> VerticalProductList(
                                products = products,
                                wishlist = uiState.wishlist,
                                onToggleWish = viewModel::toggleWishlist
                            )
                            else -> {
                                Text(
                                    text = stringResource(id = R.string.section_unknown_type, section.type),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = dp2sp(dp = 16.dp),
                                    color = "#222222".color,
                                    modifier = Modifier.padding(16.dp)
                                )
                            }
                        }

                        16.HeightMargin()
                        if (idx < uiState.sections.lastIndex) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                                    .background("#d5d5d5".color)
                            )
                        }
                    }

                    if (uiState.isLoading) {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 24.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    }
                }
            }
        }

        if (!uiState.isLoading) {
            PullRefreshIndicator(
                refreshing = uiState.isRefreshing,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
    }
}
