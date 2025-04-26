package com.kurly.assignment.data.main.repository

import com.kurly.assignment.data.main.mapper.ProductMapper
import com.kurly.assignment.data.main.mapper.SectionMapper
import com.kurly.assignment.data.main.remote.MainApi
import com.kurly.assignment.domain.main.model.Product
import com.kurly.assignment.domain.main.model.Section
import com.kurly.assignment.domain.main.repository.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val api: MainApi,
    private val sectionMapper: SectionMapper,
    private val productMapper: ProductMapper
) : MainRepository {

    override suspend fun getSections(page: Int): Pair<List<Section>, Int?>  {
        val response = api.getSections(page)
        val sectionList = response.data.map { sectionMapper.mapToDomain(it) }
        return sectionList to response.paging?.next_page
    }

    override suspend fun getProducts(sectionId: Int): List<Product> {
        return api.getProducts(sectionId).data.map { productMapper.mapToDomain(it) }
    }
}