package com.example.userprofile.ui.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.core.ui.components.TextFieldForm
import com.example.core.ui.components.TopAppBarSecondary
import com.example.core.ui.theme.PawpalTheme
import com.example.userprofile.ui.view.DateConverter.millisToFormattedDate
import com.example.userprofile.ui.viewmodel.ProfileStateViewModel
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object DateConverter {
    @RequiresApi(Build.VERSION_CODES.O)
    fun millisToFormattedDate(millis: Long, pattern: String = "yyyy-MM-dd"): String {
        val instant = Instant.fromEpochMilliseconds(millis)
        val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())

        val year = localDateTime.year
        val month = localDateTime.monthNumber
        val day = localDateTime.date

        return "$day"
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun EditUserProfileScreenPreview() {
    ProfileScreen(viewModel = ProfileStateViewModel(), navController = rememberNavController())
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ProfileScreen(viewModel: ProfileStateViewModel, navController: NavController) {

    viewModel.getProfileUi()
    val profileState = viewModel.profileState.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }

    val showMessage by viewModel.showMessage.collectAsState()
    val message by viewModel.message.collectAsState()

    LaunchedEffect(showMessage) {
        if (showMessage) {
            snackbarHostState.showSnackbar(
                message = message,
                duration = SnackbarDuration.Short
            )
//            viewModel._noti.value = false

        }
    }

    Scaffold(
        topBar = { TopAppBarSecondary("Perfil", navController) },
                snackbarHost = { SnackbarHost(snackbarHostState) }

    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
        ) {
            Text(
                text = "Así es como los demás te verán en pawpal.",
                style = MaterialTheme.typography.bodySmall,
            )
            Spacer(modifier = Modifier.height(16.dp))
            NameProfileField(
                value = profileState.value.name,
                onValueChange = { viewModel.onNameChanged(it) }
            )
            Spacer(modifier = Modifier.height(16.dp))
            BirthDateProfileField(
                value = profileState.value.birthDate,
                onValueChange = { viewModel.onBirthDateChanged(it) },
                viewModel = viewModel
            )
            Spacer(modifier = Modifier.height(16.dp))
            CityProfileField(
                value = profileState.value.city,
                onValueChange = { viewModel.onCityChanged(it) }
            )
            Spacer(modifier = Modifier.height(16.dp))
            BiographyProfileField(
                value = profileState.value.biography,
                onValueChange = { viewModel.onBiographyChanged(it) }
            )
            Spacer(modifier = Modifier.height(16.dp))
//            PhoneProfileField(
//                value = profileState.value.phone,
//                onValueChange = { viewModel.onPhoneChanged(it) }
//            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 16.dp, 0.dp, 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    modifier = Modifier
                        .width(180.dp)
                        .height(48.dp),
                    onClick = {
                        viewModel.updateProfileClickable()
                    }
                ) {
                    Text(
                        text = "Actualizar perfil",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

            }
//            Button(onClick = {
//                viewModel.updateProfileClickable()
//            }) {
//                Text(text = "Actualizar perfil")
//            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun NameProfileField(
    value: String,
    onValueChange: (String) -> Unit,
) {
    TextFieldForm(
        value = value,
        onValueChange = onValueChange,
        label = "Nombre",
        placeholder = "Menchito",
        supportingText = "Este es el nombre que se mostrará en tu perfil y en los correos electrónicos.",
        errorList = listOf(),
        isError = false,
        keyboardType = KeyboardType.Text,
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BirthDateProfileField(
    value: String,
    onValueChange: (String) -> Unit,
    viewModel: ProfileStateViewModel
) {
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = System.currentTimeMillis(),
        selectableDates = object : SelectableDates {  // Envolver en un objeto SelectableDates
            override fun isSelectableDate(dateInMillis: Long): Boolean {
                return dateInMillis <= System.currentTimeMillis()
            }
        },
        yearRange = 2000..2024

    )

    fun convertMillisToDate(millis: Long): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = millis
        calendar.add(Calendar.DAY_OF_YEAR, 1) // Suma un día
//        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
//        return formatter.format(Date(millis))
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        formatter.timeZone = java.util.TimeZone.getDefault() // Usa la zona horaria del sistema
//        return formatter.format(Date(millis))
        return formatter.format(calendar.time)
    }

    TextFieldForm(
        value = value,
        onValueChange = onValueChange,
        label = "Fecha de nacimiento",
        placeholder = "Fecha de nacimiento",
        supportingText = "Tu fecha de nacimiento se utiliza para calcular tu edad.",
        errorList = listOf(),
        isError = false,
        keyboardType = KeyboardType.Text,
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
//            confirmButton = {
//                TextButton(onClick = {
//                    if (datePickerState.selectedDateMillis != null) {
//                        val date = millisToFormattedDate(datePickerState.selectedDateMillis!!)
//                        viewModel.onBirthDateChanged(date)
//                    }
//                    showDatePicker = false
//                }) {
//                    Text("OK")
//                }
//            },
            confirmButton = {
                TextButton(onClick = {
                    if (datePickerState.selectedDateMillis != null) {
                        val date = convertMillisToDate(datePickerState.selectedDateMillis!!)
                        viewModel.onBirthDateChanged(date)
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
}

@Composable
fun CityProfileField(
    value: String,
    onValueChange: (String) -> Unit,
) {
    TextFieldForm(
        value = value,
        onValueChange = onValueChange,
        label = "Ciudad",
        placeholder = "Ocosingo",
        supportingText = "Tu ciudad actual para personalizar tu perfil.",
        errorList = listOf(),
        isError = false,
        keyboardType = KeyboardType.Text,
    )
}

@Composable
fun BiographyProfileField(
    value: String,
    onValueChange: (String) -> Unit,
) {
    TextFieldForm(
        value = value,
        onValueChange = onValueChange,
        label = "Biografía",
        placeholder = "Me gustan los perros.",
        supportingText = "Una breve descripción de ti que será visible en tu perfil.",
        errorList = listOf(),
        isError = false,
        keyboardType = KeyboardType.Text,
    )
}

@Composable
fun PhoneProfileField(
    value: String,
    onValueChange: (String) -> Unit,
) {
    TextFieldForm(
        value = value,
        onValueChange = onValueChange,
        label = "Teléfono",
        placeholder = "9191728149",
        supportingText = "Tu número de teléfono para contacto adicional.",
        errorList = listOf(),
        isError = false,
        keyboardType = KeyboardType.Phone,
    )
}



