package com.salman.myapplication.data.network


import com.salman.myapplication.data.models.QuestionItem
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET

interface TriviaApiService {
    @GET("itmmckernan/triviaJSON/master/world.json")
    suspend fun getQuestions(): Response<List<QuestionItem>>
}