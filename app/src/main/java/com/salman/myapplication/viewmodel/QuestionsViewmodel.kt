package com.salman.myapplication.viewmodel


import android.util.Log
import androidx.lifecycle.ViewModel
import com.salman.myapplication.data.models.QuestionItem
import com.salman.myapplication.data.repository.QuestionsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class QuestionsViewmodel @Inject constructor(private val repo: QuestionsRepository) :ViewModel(){

        fun getTriviaQuestions(): Flow<List<QuestionItem>> = flow {
        try {
           repo.getQuestions().collect {
               Log.d("question","$it")

               emit(it)
            }

        } catch (e: Exception) {
            Log.d("question","${e.message}")

            emit(emptyList<QuestionItem>())
        }
    }
}