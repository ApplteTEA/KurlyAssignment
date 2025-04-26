package com.kurly.assignment.di

import com.kurly.assignment.domain.main.repository.MainRepository
import com.kurly.assignment.domain.main.usecase.FetchMainProductsUseCase
import com.kurly.assignment.domain.main.usecase.FetchMainSectionsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideFetchMainSectionsUseCase(repository: MainRepository): FetchMainSectionsUseCase {
        return FetchMainSectionsUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideFetchMainProductsUseCase(repository: MainRepository): FetchMainProductsUseCase {
        return FetchMainProductsUseCase(repository)
    }
}