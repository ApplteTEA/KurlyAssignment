package com.kurly.assignment.data.main.remote

import com.kurly.assignment.data.main.model.ProductResponse
import com.kurly.assignment.data.main.model.SectionResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MainApi {

    @GET("sections")
    suspend fun getSections(@Query("page") page: Int): SectionResponse

    @GET("section/products")
    suspend fun getProducts(@Query("sectionId") id: Int): ProductResponse
}