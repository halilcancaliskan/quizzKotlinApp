import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun Home() {
    MaterialTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Quizz", style = MaterialTheme.typography.h4)
            Text(
                "A simple quizz to discover KMP, KMM, and Compose.",
                style = MaterialTheme.typography.body1
            )

            Spacer(modifier = Modifier.size(16.dp))

            Button(
                onClick = { /* Handle button click here */ },
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Start the Quizz")
            }
        }
    }
}
