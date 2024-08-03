package com.salman.myapplication.data.repository

import com.salman.myapplication.data.models.QuestionItem
import com.salman.myapplication.data.network.TriviaApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class QuestionsRepositoryImpl @Inject constructor(
    private val triviaApiService: TriviaApiService

) : QuestionsRepository {
    override suspend fun getQuestions(): Flow<List<QuestionItem>> = flow {
        try {
            val response = triviaApiService.getQuestions()
            if (response.isSuccessful) {
                emit(response.body() ?: emptyList())
            } else {
                throw Exception("API call failed with code ${response.code()}")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}