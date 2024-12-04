package com.example.weight.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.core.ui.components.DrawerContent
import com.example.core.ui.components.TopAppBarPrimary
import com.example.core.ui.viewmodel.SelectedDogViewModel
import com.example.core.utils.TimestamptzFormatter
import com.example.weight.domain.model.Weight
import com.example.weight.domain.model.alimentation.Alimentation
import com.example.weight.ui.viewmodel.AlimentationViewModel
import com.example.weight.ui.viewmodel.WeightViewModel
import com.github.tehras.charts.line.LineChart
import com.github.tehras.charts.line.LineChartData
import com.github.tehras.charts.line.renderer.line.SolidLineDrawer
import com.github.tehras.charts.line.renderer.point.FilledCircularPointDrawer
import com.github.tehras.charts.piechart.animation.simpleChartAnimation
import java.time.Instant
import java.time.ZoneId

@Composable
fun AlimentationScreen(
    navController: NavController,
    alimentationViewModel: AlimentationViewModel = hiltViewModel(),
    selectedDogViewModel: SelectedDogViewModel = hiltViewModel()
) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val alimentationHistory by alimentationViewModel.alimentationHistory.collectAsState()

    val selectedDog by selectedDogViewModel.selectedDog.collectAsState()

    val startDate by alimentationViewModel.startDate.collectAsState()
    val endDate by alimentationViewModel.endDate.collectAsState()
    val selectedDates by alimentationViewModel.selectedDates.collectAsState()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerContent(navController)
            }
        },
    ) {
        Scaffold(
            topBar = {
                TopAppBarPrimary("Peso de la mascota", drawerState, scope)
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState()),
//                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                BetweenDatesAlimentation(
                    startDateChanged = { alimentationViewModel.onStartDateChanged(it) },
                    endDateChanged = { alimentationViewModel.onEndDateChanged(it) }
                )
                WeightChartAlimentation(alimentationHistory, selectedDog, navController)
                CustomButtomAlimentation(
                    onClick = {
                        alimentationViewModel.getAlimentationHistory(selectedDog ?: 0, startDate, endDate)
                    },
                    selectedDog = selectedDog,
                    selectedDates = selectedDates

                )
            }
        }
    }
}

@Composable
fun WeightChartAlimentation(weightHistory: List<Alimentation>?, selectedDog: Int?, navController: NavController) {

    val timestamptzFormatter = TimestamptzFormatter()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, bottom = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Control de peso",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(16.dp)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            if (selectedDog == null) {
                Text(
                    text = "Aun no has seleccionado una mascota",
                    style = MaterialTheme.typography.labelMedium
                )
                Button(
                    onClick = {
                        navController.navigate("home")
                    },
                ) {
                    Text("Ir a home")
                }

            }

            if (weightHistory == null) {

                Text(
                    text = "...",
                    style = MaterialTheme.typography.labelMedium
                )


            } else if (weightHistory.isEmpty()) {

                Text(
                    text = "No se tiene registro de alimentación",
                    style = MaterialTheme.typography.labelMedium
                )

            } else {
//                Text(
//                    text = "Historial del peso:",
//                    style = MaterialTheme.typography.titleMedium
//                )
//                Spacer(modifier = Modifier.height(32.dp))

                val points = weightHistory.map { weight ->
                    LineChartData.Point(weight.food_amount.toFloat(), weight.created_at )
                }

                val line = SolidLineDrawer(
                    color = MaterialTheme.colorScheme.primary
                )

                Row(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "Cantidad (gramos)",
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.rotate(-90f)
                    )
                    LineChart(
                        linesChartData = listOf(LineChartData(points = points, lineDrawer = line)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
//                            .padding(start = 0.dp, end = 16.dp, top = 8.dp, bottom = 8.dp),
                        animation = simpleChartAnimation(),
                        pointDrawer = FilledCircularPointDrawer(color = MaterialTheme.colorScheme.secondary),
//                        horizontalOffset = -2f,
                        labels = weightHistory.map { timestamptzFormatter.getFormattedDate(it.created_at) }
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Fecha",
                        style = MaterialTheme.typography.labelMedium,
                    )
                }
            }
//            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun BetweenDatesPreview() {
//    BetweenDates(
//        startDateChanged = {},
//        endDateChanged = {}
//    )
//}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BetweenDatesAlimentation(
    startDateChanged: (String) -> Unit,
    endDateChanged: (String) -> Unit
) {

    val startDate = remember { mutableStateOf("") }
    val endDate = remember { mutableStateOf("") }

    var showDialog by remember { mutableStateOf(false) }
    var showDialogFinal by remember { mutableStateOf(false) }

    val textFieldOneState = rememberDatePickerState(
        initialSelectedDateMillis = System.currentTimeMillis(),
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(dateInMillis: Long): Boolean {
                return dateInMillis <= System.currentTimeMillis()
            }
        },
        yearRange = 2000..2024
    )
    val textFieldTwoState = rememberDatePickerState(
        initialSelectedDateMillis = System.currentTimeMillis(),
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(dateInMillis: Long): Boolean {
                return dateInMillis <= System.currentTimeMillis()
            }
        },
        yearRange = 2000..2024
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 0.dp),
//        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = startDate.value,
            onValueChange = {},
            label = { Text("Fecha inicio") },
            readOnly = true,
            modifier = Modifier.weight(1f),
            trailingIcon = {
                IconButton(
                    onClick = {
                        showDialog = true
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Abrir selector de fecha"
                    )
                }
            }
        )
        Spacer(modifier = Modifier.width(16.dp))
        OutlinedTextField(
            value = endDate.value,
            onValueChange = {},
            label = { Text("Fecha final") },
            readOnly = true,
            modifier = Modifier.weight(1f),
            trailingIcon = {
                IconButton(
                    onClick = {
                        showDialogFinal = true
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Abrir selector de fecha"
                    )
                }
            }
        )
    }
    if (showDialog) {
        DatePickerDialog(
            onDismissRequest = {
                showDialog = false
            },
            confirmButton = {
                Button(
                    onClick = {
                        showDialog = false
                    }
                ) {
                    Text("Aceptar")
                }
            },
            dismissButton = {
                OutlinedButton(
                    onClick = {
                        showDialog = false
                    }
                ) {
                    Text("Cancelar")
                }
            }
        ) {
            DatePicker(state = textFieldOneState)
        }
    }
    if (showDialogFinal) {
        DatePickerDialog(
            onDismissRequest = {
                showDialogFinal = false
            },
            confirmButton = {
                Button(
                    onClick = {
                        showDialogFinal = false
                    }
                ) {
                    Text("Aceptar")
                }
            },
            dismissButton = {
                OutlinedButton(
                    onClick = {
                        showDialogFinal = false
                    }
                ) {
                    Text("Cancelar")
                }
            }
        ) {
            DatePicker(state = textFieldTwoState)
        }
    }
    val dateOne = textFieldOneState.selectedDateMillis
    val dateTwo = textFieldTwoState.selectedDateMillis

    dateOne?.let {
        val localDate = Instant.ofEpochMilli(it).atZone(ZoneId.of("UTC")).toLocalDate()
        startDate.value =  "${localDate.dayOfMonth}-${localDate.monthValue}-${localDate.year}"
        startDateChanged("${localDate.year}-${localDate.monthValue}-${localDate.dayOfMonth}")
    }

    dateTwo?.let {
        val localDate = Instant.ofEpochMilli(it).atZone(ZoneId.of("UTC")).toLocalDate()
        endDate.value =  "${localDate.dayOfMonth}-${localDate.monthValue}-${localDate.year}"
        endDateChanged("${localDate.year}-${localDate.monthValue}-${localDate.dayOfMonth}")
    }
}

@Composable
fun CustomButtomAlimentation(
    onClick: () -> Unit,
    selectedDog: Int?,
    selectedDates: Boolean
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(
            onClick =  onClick,
            enabled = selectedDog != null && selectedDates
        ) {
            Text("Ver historial")
        }
    }
}
