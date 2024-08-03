package com.salman.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import com.salman.myapplication.data.models.QuestionItem
import com.salman.myapplication.ui.screens.QuestionScreen
import com.salman.myapplication.ui.theme.MyApplicationTheme
import com.salman.myapplication.viewmodel.QuestionsViewmodel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    QuestionScreen(
                        viewModel = hiltViewModel<QuestionsViewmodel>(),
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(viewModel: QuestionsViewmodel = hiltViewModel<QuestionsViewmodel>()) {

    val questions by viewModel.getTriviaQuestions().collectAsState(initial = emptyList())
    LazyColumn {
        items(questions) { question ->
            Log.d("question", "$question")
            QuestionItemView(question)
        }
    }

}

@Composable
fun QuestionItemView(questionItem: QuestionItem) {
    // Display a single question item
    Text(text = questionItem.question)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        val viewModel = hiltViewModel<QuestionsViewmodel>()

        Greeting(viewModel = hiltViewModel<QuestionsViewmodel>())
    }
}