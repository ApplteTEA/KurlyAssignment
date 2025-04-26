package com.kurly.assignment.data.main.mapper

import com.kurly.assignment.data.main.model.SectionDto
import com.kurly.assignment.domain.main.model.Section
import javax.inject.Inject

class SectionMapper @Inject constructor() {
    fun mapToDomain(dto: SectionDto): Section = Section(
        id = dto.id,
        title = dto.title,
        type = dto.type
    )
}