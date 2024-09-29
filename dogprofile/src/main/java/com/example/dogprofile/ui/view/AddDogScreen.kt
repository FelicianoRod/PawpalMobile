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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
//import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.core.ui.components.DrawerContent
import com.example.core.ui.components.TextFieldForm
import com.example.core.ui.components.TopAppBarSecondary
import com.example.core.ui.theme.PawpalTheme
import com.example.dogprofile.domain.Breed
import com.example.dogprofile.ui.viewmodel.AddDogState
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Preview(showBackground = true)
@Composable
fun AddNewDogScreenPreview() {
    AddDogScreen(navController = rememberNavController(), viewModel = AddDogState())
}

@Composable
fun AddDogScreen(navController: NavController, viewModel: AddDogState) {

    val dogFormState by viewModel.dogFormState.collectAsState()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val scrollState = rememberScrollState()

    PawpalTheme {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet {
                    DrawerContent(navController)
                }
            },
        ) {
            Scaffold(
                topBar = { TopAppBarSecondary("Añadir mascota", navController) }
            ) { innerPadding ->
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                        .padding(16.dp),
                ) {
//                    Text(
//                        text = "Añadir mascota",
//                        style = MaterialTheme.typography.titleLarge,
//                        color = MaterialTheme.colorScheme.primary
//                    )
                    Text(
                        text = "Añade la información de tu mascota a continuación.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    NameDogField(
                        value = dogFormState.name,
                        onValueChange = { viewModel.onNameChanged(it) }
                        )
//                    TextFieldCustom()
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
                    IsOwnerField(
                        value = dogFormState.isOwner,
                        selected = { viewModel.onIsOwnerChanged(it) }
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    BirthdateField(
                        value = dogFormState.birthdate,
                        onValueChanged = { viewModel.onBirthdateChanged(it) },
                        viewModel = viewModel
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    GenderField(
                        value = dogFormState.gender,
                        selected = { viewModel.onGenderChanged(it) }
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    BreedField(
                        breedsList = dogFormState.breeds,
                        onValueChanged = { viewModel.onBreedIdSelected(it) }
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    DescriptionField(
                        value = dogFormState.description,
                        onValueChanged = { viewModel.onDescriptionChanged(it) }
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    WeightField(
                        value = dogFormState.weight.toString(),
                        onValueChanged = { viewModel.onWeightChanged(it.toDouble()) }
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    TagsDogField()
                    Button(
                        onClick = {
                            viewModel.addDog()
                        }
                    )  {
                        Text(text = "Añadir mascota")
                    }


                }
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
fun WeightField(
    value: String,
    onValueChanged: (String) -> Unit
) {
    TextFieldForm(
        value = value,
        onValueChange = onValueChanged,
        label = "Peso",
        placeholder= "5.5"
    )
}

@Composable
fun DescriptionField(
    value: String,
    onValueChanged: (String) -> Unit
) {
    TextFieldForm(
        value = value,
        onValueChange = onValueChanged,
        label = "Detalla a tu mascota",
        placeholder = "Añade una descripción de tu mascota"
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BreedField(breedsList: List<Breed>, onValueChanged: (Int) -> Unit) {
    // Estado para el menú desplegable
    var expanded by remember { mutableStateOf(false) }

    // Estado para el texto seleccionado
    var selectedOption by remember { mutableStateOf("Selecciona la raza") }

    var selectedOptionId by remember { mutableIntStateOf(0) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        TextField(
            value = selectedOption,
//            onValueChange = { selectedOption = it },
//            onValueChange = onValueChanged,
            onValueChange = {},
            readOnly = true,
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            label = { Text("Selecciona una opción") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            breedsList.forEach { breed ->
                DropdownMenuItem(
                    text = { Text(breed.name) },
                    onClick = {
                        selectedOption = breed.name
                        selectedOptionId = breed.id
                        onValueChanged(selectedOptionId)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun GenderField(
    value: Boolean,
    selected: (Boolean) -> Unit
) {
    var selectedOption by remember { mutableStateOf(true) }

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
                selected = value,
                onClick = { selected(true) }
            )
            Text("Macho")
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = !value,
                onClick = { selected(false) }
            )
            Text("Hembra")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BirthdateField(
    value: String,
    onValueChanged: (String) -> Unit,
    viewModel: AddDogState
) {
    fun convertMillisToDate(millis: Long): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return formatter.format(Date(millis))
    }

    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
//    val selectedDate = datePickerState.selectedDateMillis?.let {
//        convertMillisToDate(it)
//    } ?: ""

    TextFieldForm(
        value = value,
        onValueChange = onValueChanged,
        label = "Fecha de nacimiento",
        placeholder = "Fecha de nacimiento",
        modifier = Modifier.clickable {
            showDatePicker = true
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = "Fecha de nacimiento",
                modifier = Modifier.clickable {
                    showDatePicker = true
                }
            )
        }
    )
    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    if (datePickerState.selectedDateMillis != null) {
                        val date = convertMillisToDate(datePickerState.selectedDateMillis!!)
                        viewModel.onBirthdateChanged(date)
                    }
                    showDatePicker = false
                }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

//    Text(
//        text = "Fecha de nacimiento",
//        style = MaterialTheme.typography.bodyLarge
//    )
//    Box(
//        modifier = Modifier.fillMaxWidth()
//    ) {
//        OutlinedTextField(
//            value = selectedDate,
//            onValueChange = { },
//            label = { Text("DOB") },
//            readOnly = true,
//            trailingIcon = {
//                IconButton(onClick = { showDatePicker = !showDatePicker }) {
//                    Icon(
//                        imageVector = Icons.Default.DateRange,
//                        contentDescription = "Select date"
//                    )
//                }
//            },
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(64.dp)
//        )
//        if (showDatePicker) {
//            Popup(
//                onDismissRequest = { showDatePicker = false },
//                alignment = Alignment.TopStart
//            ) {
//                Box(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .offset(y = 64.dp)
//                        .shadow(elevation = 4.dp)
//                        .background(MaterialTheme.colorScheme.surface)
//                        .padding(16.dp)
//                ) {
//                    DatePicker(
//                        state = datePickerState,
//                        showModeToggle = false
//                    )
//                }
//            }
//        }
//    }
}

@Composable
fun IsOwnerField(
    value: Boolean,
    selected: (Boolean) -> Unit
) {

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
                selected = value,
                onClick = { selected(true) }
            )
            Text("Sí")
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = !value,
                onClick = { selected(false) }
            )
            Text("No")
        }
    }
}

//@Composable
//fun TextFieldCustom() {
//    OutlinedTextField(
//        value = "",
//        onValueChange = { },
//        label = { Text("Ingrese el nombre de su mascota") },
//        modifier = Modifier.fillMaxWidth()
//    )
//}
