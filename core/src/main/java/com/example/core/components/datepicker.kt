import android.app.DatePickerDialog
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.util.*

@Composable
fun DatePickerComposable() {
    // Estado para almacenar la fecha seleccionada
    var selectedDate by remember { mutableStateOf("Selecciona una fecha") }

    // Contexto actual
    val context = LocalContext.current

    // Obtener la fecha actual para inicializar el DatePicker
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    // Configurar el DatePickerDialog
    val datePickerDialog = DatePickerDialog(
        context,
        { _, selectedYear, selectedMonth, selectedDay ->
            // Actualizar el estado con la fecha seleccionada
            selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
        },
        year,
        month,
        day
    )

    // UI del Composable
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Fecha seleccionada: $selectedDate")

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            datePickerDialog.show() // Mostrar el selector de fecha al hacer clic en el botón
        }) {
            Text(text = "Seleccionar fecha")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDatePickerComposable() {
    DatePickerComposable()
}
