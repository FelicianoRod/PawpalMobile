import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker = findViewById<Button>(R.id.btnDatePicker)
        val txtDate = findViewById<TextView>(R.id.txtDate)

        btnDatePicker.setOnClickListener {
            // Obtenemos la fecha actual
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            // Creamos el DatePickerDialog
            val datePickerDialog = DatePickerDialog(
                this,
                { _, selectedYear, selectedMonth, selectedDay ->
                    // Mostramos la fecha seleccionada en el TextView
                    txtDate.text = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                },
                year,
                month,
                day
            )

            // Mostramos el DatePickerDialog
            datePickerDialog.show()
        }
    }
}
