package com.salman.myapplication.data.repository

import com.salman.myapplication.data.models.QuestionItem
import kotlinx.coroutines.flow.Flow


    interface QuestionsRepository {
        suspend fun getQuestions() :  Flow<List<QuestionItem>>
    }