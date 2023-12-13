import io.ktor.utils.io.core.Closeable
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import network.QuizAPI
import network.data.Question

class QuizRepository : Closeable {

    private val quizAPI = QuizAPI()
    private val coroutineScope = CoroutineScope(Job() + Dispatchers.IO)
    private val _questionState = MutableStateFlow(emptyList<Question>())
    val questionState get() = _questionState

    init {
        updateQuiz()
    }

    private suspend fun fetchQuiz(): List<Question> = quizAPI.getAllQuestions().questions

    private fun updateQuiz() {
        coroutineScope.launch {
            try {
                _questionState.value = fetchQuiz()
            } catch (e: Exception) {
                // Handle the exception (e.g., log it or display an error message)
                e.printStackTrace()
            }
        }
    }

    override fun close() {
        coroutineScope.cancel()
    }
}
