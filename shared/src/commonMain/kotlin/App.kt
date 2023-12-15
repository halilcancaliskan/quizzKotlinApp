import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import org.jetbrains.compose.resources.ExperimentalResourceApi

private val repository = QuizRepository()

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    MaterialTheme {
        val questions = repository.questionState.collectAsState()

        if (questions.value.isNotEmpty()) {
            QuestionScreen(questions.value)
        }
        //Home()
        //Score(8, 10, {})
    }
}

expect fun getPlatformName(): String
