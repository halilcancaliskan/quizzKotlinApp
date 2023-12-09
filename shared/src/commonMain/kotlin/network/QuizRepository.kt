import io.ktor.utils.io.core.Closeable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import network.QuizAPI
import network.data.Question

class QuizRepository : Closeable {

    private val quizAPI = QuizAPI()
    private val coroutineScope = CoroutineScope(Job() + Dispatchers.IO)
    private var _questionState = MutableStateFlow(emptyList<Question>())
    var questionState = _questionState

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
