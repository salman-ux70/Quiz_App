package com.salman.myapplication.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.salman.myapplication.R
import com.salman.myapplication.viewmodel.QuestionsViewmodel
import com.salman.myapplication.Components.QuestionWidget
import com.salman.myapplication.Components.ShowProgress


@Composable
fun QuestionScreen(
    modifier: Modifier = Modifier,
    viewModel: QuestionsViewmodel = hiltViewModel<QuestionsViewmodel>()
) {
    val questions by viewModel.getTriviaQuestions().collectAsState(initial = emptyList())
    var currentIndex by remember {
        mutableStateOf(0)
    }

    var correctAnswer by remember {
        mutableStateOf(0)
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ShowProgress(currentIndex)

            val context = LocalContext.current
            if (questions.isNotEmpty()) {
                QuestionWidget(questions[currentIndex]){
                    if (it){
                        Toast.makeText(context, "true", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(context, "false", Toast.LENGTH_SHORT).show()
                    }
                    currentIndex++
                }

            } else {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = stringResource(id = R.string.loading),
                        style = MaterialTheme.typography.bodyLarge,
                        )
                }

            }


        }

    }

}