import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Score(score: Int, totalQuestions: Int, onRetakeClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ScoreText(score, totalQuestions)

        RetakeButton(onRetakeClick)
    }
}

@Composable
private fun ScoreText(score: Int, totalQuestions: Int) {
    Text(
        text = "Score: $score / $totalQuestions",
        style = MaterialTheme.typography.h5,
        modifier = Modifier.padding(bottom = 16.dp)
    )
}

@Composable
private fun RetakeButton(onRetakeClick: () -> Unit) {
    Button(
        onClick = {
            onRetakeClick()
            println("Retake the quizz")
                  },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Retake the quizz")
    }
}
