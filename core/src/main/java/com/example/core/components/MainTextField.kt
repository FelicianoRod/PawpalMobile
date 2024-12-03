import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import io.ktor.websocket.Frame

@Composable
fun MainTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    label: String = "Enter text here"
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Frame.Text(label) },
        modifier = Modifier.fillMaxWidth()
    )
}
