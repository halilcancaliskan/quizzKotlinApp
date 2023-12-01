import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Score: $score / $totalQuestions",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(16.dp)
        )

        Button(
            onClick = { onRetakeClick() },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Retake the Quiz")
        }
    }
}
