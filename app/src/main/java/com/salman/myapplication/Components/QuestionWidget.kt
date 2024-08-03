package com.salman.myapplication.Components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.salman.myapplication.data.models.QuestionItem


@Composable
fun QuestionWidget(
    question: QuestionItem,
    onNextButtonClicked: (Boolean) -> Unit,
) {
    val choices = question.choices
    var selectedOption by remember {
        mutableStateOf(choices.firstOrNull()?:"")
    }

    Surface(
        onClick = { },
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 3.dp,
        modifier = Modifier.padding(top = 12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = question.question)
            QuestionOption(choices, selectedOption = selectedOption) { newSelection ->
                selectedOption = newSelection
            }
            Button(modifier = Modifier.padding(top = 12.dp), onClick = {
                onNextButtonClicked(selectedOption == question.answer)
            }) {
                Text(text = "Next", style = MaterialTheme.typography.titleSmall)
            }
        }

    }
}

@Composable
fun QuestionOption(
    choices: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
Log.d("selected_option","$selectedOption")
var selectedIndex = 0
    Column {
        choices.forEach { text ->
            selectedIndex ++
            Row(
                Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (if (selectedIndex == 0) true else false),
                        onClick = {
                            onOptionSelected(text)
                        }
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = { onOptionSelected(text) }
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodySmall.merge(),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

        }
    }
}