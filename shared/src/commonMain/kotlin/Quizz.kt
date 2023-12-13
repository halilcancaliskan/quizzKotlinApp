import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import network.data.Answer
import network.data.Question

@Composable
internal fun CustomButton(imageVector: ImageVector, label: String) {
    Icon(
        imageVector,
        contentDescription = "Localized description",
        Modifier.padding(end = 15.dp)
    )
    Text(label)
}

@Composable
internal fun QuestionCard(question: Question) {
    Card() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 10.dp)
        ) {
            Text(
                modifier = Modifier.padding(all = 10.dp),
                text = question.label,
                fontSize = 25.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
internal fun AnswerOptions(answers: List<Answer>, selectedAnswer: Int, onAnswerSelected: (Int) -> Unit) {
    Column(modifier = Modifier.selectableGroup()) {
        answers.forEach { answer ->
            Row(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    modifier = Modifier.padding(end = 16.dp),
                    selected = (selectedAnswer == answer.id),
                    onClick = { onAnswerSelected(answer.id) },
                )
                Text(text = answer.label)
            }
        }
    }
}

@Composable
internal fun QuestionScreen(questions: List<Question>) {
    var questionProgress by remember { mutableStateOf(0) }
    var selectedAnswer by remember { mutableStateOf(1) }
    var score by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        QuestionCard(questions[questionProgress])

        AnswerOptions(
            answers = questions[questionProgress].answers,
            selectedAnswer = selectedAnswer,
            onAnswerSelected = { selectedAnswer = it }
        )

        Column(
            modifier = Modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Button(
                modifier = Modifier.padding(bottom = 20.dp),
                onClick = {
                    if (selectedAnswer == questions[questionProgress].correctAnswerId) {
                        score++
                    }
                    if (questionProgress < questions.size - 1) {
                        questionProgress++
                        selectedAnswer = 1
                    } else {
                        // Print the score to the console
                        println("Final Score: $score")
                    }
                }
            ) {
                if (questionProgress < questions.size - 1) CustomButton(Icons.Filled.ArrowForward, "Next")
                else CustomButton(Icons.Filled.Done, "Done")
            }

            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth().height(20.dp),
                progress = questionProgress.div(questions.size.toFloat()).plus(1.div(questions.size.toFloat()))
            )
        }
    }
}
