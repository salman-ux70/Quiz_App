package com.salman.myapplication.di

import com.salman.myapplication.data.network.TriviaApiService
import com.salman.myapplication.data.repository.QuestionsRepository
import com.salman.myapplication.data.repository.QuestionsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Url
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Provides
    fun provideBaseUrl(): String =
        "https://raw.githubusercontent.com/"

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideTriviaApiService(retrofit: Retrofit): TriviaApiService {
        return retrofit.create(TriviaApiService::class.java)
    }

    @Provides
    fun providesRepository(
        questionsRepositoryImpl: QuestionsRepositoryImpl
    ) : QuestionsRepository {
        return  questionsRepositoryImpl
    }
}