package com.kurly.assignment.di

import android.content.Context
import com.kurly.assignment.data.main.mapper.ProductMapper
import com.kurly.assignment.data.main.mapper.SectionMapper
import com.kurly.assignment.data.main.repository.MainRepositoryImpl
import com.kurly.assignment.domain.main.repository.MainRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMainRepository(impl: MainRepositoryImpl): MainRepository
}