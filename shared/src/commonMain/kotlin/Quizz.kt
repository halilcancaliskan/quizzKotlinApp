import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

enum class QuizzOption(val text: String) {
    Yes("Yes"),
    No("No")
}

@Composable
fun QuizzOptionRadioButton(
    option: QuizzOption,
    selectedOption: QuizzOption?,
    onOptionSelected: (QuizzOption) -> Unit
) {
    val isSelected = option == selectedOption
    val radioButtonColor = if (isSelected) MaterialTheme.colors.primary else MaterialTheme.colors.secondary

    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isSelected,
            onClick = { onOptionSelected(option) },
            colors = RadioButtonDefaults.colors(selectedColor = radioButtonColor)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = option.text)
    }
}

data class QuizQuestion(val text: String, val options: List<QuizzOption>, val correctAnswer: QuizzOption)

@Composable
fun Quizz() {
    val questions = listOf(
        QuizQuestion("Android is a great platform?", listOf(QuizzOption.Yes, QuizzOption.No), QuizzOption.Yes),
        QuizQuestion("Do you like coding in Kotlin?", listOf(QuizzOption.Yes, QuizzOption.No), QuizzOption.No),
        QuizQuestion("Have you developed an Android app before?", listOf(QuizzOption.Yes, QuizzOption.No), QuizzOption.Yes)
    )

    var currentQuestionIndex by remember { mutableStateOf(0) }
    val currentQuestion = questions.getOrNull(currentQuestionIndex)

    var selectedOption by remember { mutableStateOf<QuizzOption?>(null) }
    var score by remember { mutableStateOf(0) }

    MaterialTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            currentQuestion?.let {
                Text(text = it.text, style = MaterialTheme.typography.h5)

                // Radio buttons for "Yes" and "No"
                QuizzOptionRadioButton(
                    option = QuizzOption.Yes,
                    selectedOption = selectedOption,
                    onOptionSelected = { selectedOption = it }
                )
                QuizzOptionRadioButton(
                    option = QuizzOption.No,
                    selectedOption = selectedOption,
                    onOptionSelected = { selectedOption = it }
                )

                // Next button
                Button(
                    onClick = {
                        if (selectedOption != null) {
                            // Check if the selected option is correct
                            if (selectedOption == currentQuestion.correctAnswer) {
                                score++
                            }

                            // Move to the next question or finish the quiz
                            if (currentQuestionIndex < questions.size - 1) {
                                currentQuestionIndex++
                                selectedOption = null // Reset selected option for the next question
                            } else {
                                // Quiz finished, you can handle the completion here
                                // For example, display the score
                                println("Quiz completed. Your score: $score")
                            }
                        }
                    },
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(text = "Next")
                }
            }
        }
    }
}
