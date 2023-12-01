import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.serialization.Serializable
import network.data.Question

enum class QuizOption(val text: String) {
    Yes("Yes"),
    No("No")
}

@Composable
fun QuizOptionRadioButton(
    option: QuizOption,
    selectedOption: QuizOption?,
    onOptionSelected: (QuizOption) -> Unit
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
@Serializable
data class QuizQuestion(val text: String, val options: List<QuizOption>, val correctAnswer: QuizOption)

@Composable
fun Quizz() {
    val questions = listOf(
        QuizQuestion(
            text = "Do you like Compose?",
            options = listOf(QuizOption.Yes, QuizOption.No),
            correctAnswer = QuizOption.Yes
        ),
        QuizQuestion(
            text = "Do you like Kotlin?",
            options = listOf(QuizOption.Yes, QuizOption.No),
            correctAnswer = QuizOption.Yes
        ),
        QuizQuestion(
            text = "Do you like Multiplatform?",
            options = listOf(QuizOption.Yes, QuizOption.No),
            correctAnswer = QuizOption.Yes
        )
    )

    var currentQuestionIndex by remember { mutableStateOf(0) }
    val currentQuestion = questions.getOrNull(currentQuestionIndex)

    var selectedOption by remember { mutableStateOf<QuizOption?>(null) }
    var correctAnswers by remember { mutableStateOf(0) }

    MaterialTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            currentQuestion?.let { it ->
                Text(text = it.text, style = MaterialTheme.typography.h5)

                QuizOptionRadioButton(
                    option = QuizOption.Yes,
                    selectedOption = selectedOption,
                    onOptionSelected = { selectedOption = it }
                )
                QuizOptionRadioButton(
                    option = QuizOption.No,
                    selectedOption = selectedOption,
                    onOptionSelected = { selectedOption = it }
                )

                // Button text
                val buttonText = if (currentQuestionIndex < questions.size - 1) {
                    "Next"
                } else {
                    "Envoyer"
                }

                // Next button
                Button(
                    onClick = {
                        if (selectedOption != null) {
                            // Check if the selected option is correct
                            if (selectedOption == currentQuestion.correctAnswer) {
                                correctAnswers++
                            }

                            // Move to the next question or finish the quiz
                            if (currentQuestionIndex < questions.size - 1) {
                                currentQuestionIndex++
                                selectedOption = null // Reset selected option for the next question
                            } else {
                                // Quiz finished, display the score
                                val totalQuestions = questions.size
                                println("Quiz completed. Your score: $correctAnswers/$totalQuestions")
                            }
                        }
                    },
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(text = buttonText)
                }
                LinearProgressIndicator(
                    progress = (currentQuestionIndex + 1) / questions.size.toFloat(),
                    modifier = Modifier.fillMaxWidth().height(8.dp)
                )
            }
        }
    }
}

