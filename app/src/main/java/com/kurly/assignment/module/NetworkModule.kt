package com.kurly.assignment.module

import android.content.Context
import com.kurly.android.mockserver.MockInterceptor
import com.kurly.assignment.data.main.remote.MainApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideMockInterceptor(@ApplicationContext context: Context): MockInterceptor =
        MockInterceptor(context)

    @Provides
    @Singleton
    fun provideOkHttpClient(mockInterceptor: MockInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(mockInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("https://www.kurly.com")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideMainApi(retrofit: Retrofit): MainApi = retrofit.create(MainApi::class.java)
}