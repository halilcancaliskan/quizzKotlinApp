import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import network.QuizRepository
import network.data.Quiz
import org.jetbrains.compose.resources.ExperimentalResourceApi

private val repository = QuizRepository()
@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun App() {
    MaterialTheme {
        val questions = repository.questionState.collectAsState()
        if (questions.value.isNotEmpty()) {
            Quiz(questions.value)
        }

        var score by remember { mutableStateOf(0) }
        MaterialTheme {
            Scaffold {
                //Home()
                //Score(score = score, onRetakeClick = { score = 0 })
                Quizz()
            }
        }
    }
}

expect fun getPlatformName(): String
