package com.kurly.assignment.data.main.model

data class SectionResponse(
    val data: List<SectionDto>,
    val paging: PagingDto?
)

data class SectionDto(
    val id: Int,
    val title: String,
    val type: String,
    val url: String
)

data class PagingDto(
    val next_page: Int?
)