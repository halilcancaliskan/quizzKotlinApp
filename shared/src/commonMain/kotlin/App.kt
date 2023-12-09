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
            questionScreen(questions.value)
        }

    }
}

expect fun getPlatformName(): String
