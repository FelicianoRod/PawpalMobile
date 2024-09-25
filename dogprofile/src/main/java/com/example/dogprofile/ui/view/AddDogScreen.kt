package com.example.dogprofile.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
//import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.example.core.ui.components.TextFieldForm
import com.example.core.ui.theme.PawpalTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Preview(showBackground = true)
@Composable
fun AddNewDogScreenPreview() {
    AddDogScreen()
}

@Composable
fun AddDogScreen() {
    PawpalTheme {
        Scaffold() { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(16.dp),
            ) {
                Text(
                    text = "Añadir mascota",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Añade la información de tu mascota a continuación.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.padding(8.dp))
//                NameDogField()
                TextFieldCustom()
//                var isChecked by remember { mutableStateOf(false) }
//
//                Row {
//                    Text(text = if (isChecked) "Sí" else "No")
//                    Spacer(Modifier.weight(1f))
//                    Switch(
//                        checked = isChecked,
//                        onCheckedChange = { isChecked = it }
//                    )
//                }
                Spacer(modifier = Modifier.padding(8.dp))
                IsOwnerField()
                Spacer(modifier = Modifier.padding(8.dp))
                BirthdateField()
                Spacer(modifier = Modifier.padding(8.dp))
                GenderField()
                Spacer(modifier = Modifier.padding(8.dp))
                BreedField()
                Spacer(modifier = Modifier.padding(8.dp))
//                DescriptionField()
                Spacer(modifier = Modifier.padding(8.dp))
                WeightField()
                Spacer(modifier = Modifier.padding(8.dp))
                TagsDogField()



            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TagsDogField() {
    var text by remember { mutableStateOf("") }
    val tags = remember { mutableStateListOf<String>() }

    Column(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = text,
            onValueChange = { value ->
                if (value.endsWith(",")) {
                    val tag = value.trimEnd(',').trim()
                    if (tag.isNotEmpty() && tags.size < 3) {
                        tags.add(tag)
                    }
                    text = "" // Limpiar el campo para el siguiente tag
                } else {
                    text = value
                }
            },
            label = { Text("Ingresa hasta 3 tags") },
            placeholder = { Text("Escribe y separa con coma") },
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                if (text.isNotEmpty()) {
                    IconButton(onClick = { text = "" }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Limpiar texto"
                        )
                    }
                }
            },
            singleLine = true,
            maxLines = 1
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Mostrar los tags como chips con el icono de "X"
        FlowRow(
            modifier = Modifier.fillMaxWidth(),

//            mainAxisSpacing = 8.dp,
//            crossAxisSpacing = 8.dp
        ) {
            tags.forEach { tag ->
                TagChip(
                    tag = tag,
                    onRemove = { tags.remove(tag) }
                )
            }
        }
    }
}
@Composable
fun TagChip(tag: String, onRemove: () -> Unit) {
    Box(
        modifier = Modifier
            .background(Color(0xFFE0E0E0), shape = RoundedCornerShape(16.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = tag)
            Spacer(modifier = Modifier.width(4.dp))
            IconButton(onClick = onRemove, modifier = Modifier.size(16.dp)) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Eliminar tag",
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

@Composable
fun NameDogField(
    value: String,
    onValueChange: (String) -> Unit,
) {
    TextFieldForm(
        value = value,
        onValueChange = onValueChange,
        label = "Nombre de la mascota",
        placeholder = "Chuchin",
        errorList = listOf(),
        isError = false,
        keyboardType = KeyboardType.Text,

    )
}

@Composable
fun WeightField() {
    Text(
        text = "Peso de la mascota",
        style = MaterialTheme.typography.bodyLarge
    )
    OutlinedTextField(
        value = "",
        onValueChange = { },
        label = { Text("0.0 kg") },
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Composable
fun DescriptionField() {
    Text(
        text = "Detalla a tu mascota",
        style = MaterialTheme.typography.bodyLarge
    )
    OutlinedTextField(
        value = "",
        onValueChange = { },
        label = { Text("Añade una descripción de tu mascota") },
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    )
}

@Composable
fun BreedField() {
    // Estado para el menú desplegable
    var expanded by remember { mutableStateOf(false) }

    // Estado para el texto seleccionado
    var selectedOption by remember { mutableStateOf("Selecciona una opción") }

    // Lista de opciones
    val options = listOf("Opción 1", "Opción 2", "Opción 3")

    Box() {
        OutlinedTextField(
            value = selectedOption,
            onValueChange = {},
            readOnly = true,
            label = { Text("Opciones") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        selectedOption = option
                        expanded = false
                    },
                    text = { Text(option) }
                )
            }
        }
    }
}

@Composable
fun GenderField() {
    var selectedOption by remember { mutableStateOf("Sí") }
    Text(
        text = "Género de la mascota",
        style = MaterialTheme.typography.bodyLarge
    )
    Row(
        modifier = Modifier.fillMaxWidth(),
//        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = selectedOption == "Sí",
                onClick = { selectedOption = "Sí" }
            )
            Text("Macho")
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = selectedOption == "No",
                onClick = { selectedOption = "No" }
            )
            Text("Hembra")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BirthdateField() {
    fun convertMillisToDate(millis: Long): String {
        val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
        return formatter.format(Date(millis))
    }

    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    val selectedDate = datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: ""

    Text(
        text = "Fecha de nacimiento",
        style = MaterialTheme.typography.bodyLarge
    )
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = selectedDate,
            onValueChange = { },
            label = { Text("DOB") },
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { showDatePicker = !showDatePicker }) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Select date"
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
        )
        if (showDatePicker) {
            Popup(
                onDismissRequest = { showDatePicker = false },
                alignment = Alignment.TopStart
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = 64.dp)
                        .shadow(elevation = 4.dp)
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(16.dp)
                ) {
                    DatePicker(
                        state = datePickerState,
                        showModeToggle = false
                    )
                }
            }
        }
    }
}

@Composable
fun IsOwnerField() {
    var selectedOption by remember { mutableStateOf("Sí") }
    Text(
        text = "¿Es dueño de la mascota?",
        style = MaterialTheme.typography.bodyLarge
    )
    Row(
        modifier = Modifier.fillMaxWidth(),
//        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = selectedOption == "Sí",
                onClick = { selectedOption = "Sí" }
            )
            Text("Sí")
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = selectedOption == "No",
                onClick = { selectedOption = "No" }
            )
            Text("No")
        }
    }
}

@Composable
fun TextFieldCustom() {
    OutlinedTextField(
        value = "",
        onValueChange = { },
        label = { Text("Ingrese el nombre de su mascota") },
        modifier = Modifier.fillMaxWidth()
    )
}
