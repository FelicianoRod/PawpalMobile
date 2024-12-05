package com.example.weight.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.core.utils.getDistance
import com.example.weight.domain.model.vaccine.Vaccine
import com.example.weight.domain.model.walk.Walk
import com.example.weight.ui.viewmodel.VaccineViewModel
import com.github.tehras.charts.line.LineChart
import com.github.tehras.charts.line.LineChartData
import com.github.tehras.charts.line.renderer.line.SolidLineDrawer
import com.github.tehras.charts.line.renderer.point.FilledCircularPointDrawer
import com.github.tehras.charts.piechart.animation.simpleChartAnimation
import java.time.Instant
import java.time.ZoneId

@Composable
fun VaccineScreen(
    navController: NavController,
    vaccineViewModel: VaccineViewModel = hiltViewModel(),
    selectedDogViewModel: SelectedDogViewModel = hiltViewModel()
) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val vaccineHistory by vaccineViewModel.vaccineHistory.collectAsState()
    val nextVaccine by vaccineViewModel.nextVaccine.collectAsState()

    val selectedDog by selectedDogViewModel.selectedDog.collectAsState()

    val startDate by vaccineViewModel.startDate.collectAsState()
    val endDate by vaccineViewModel.endDate.collectAsState()
    val selectedDates by vaccineViewModel.selectedDates.collectAsState()

    LaunchedEffect(Unit) {
        vaccineViewModel.getNextVaccine(selectedDog ?: 0)
    }

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
                TopAppBarPrimary("Vacunas", drawerState, scope)
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
                    .padding(start = 16.dp, end = 16.dp, bottom = 0.dp, top = 16.dp)
            ) {
                // Próxima aplicación
                Text(
                    text = "Próxima aplicación",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.tertiary
                )
                nextVaccine?.forEach { vaccine ->
                    NextVaccineCard(vaccine = vaccine)
                }

                // Consultar vacunas
                Text(
                    text = "Historial de vacunación",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.tertiary
                )
                BetweenDatesVaccine(
                    startDateChanged = { vaccineViewModel.onStartDateChanged(it) },
                    endDateChanged = { vaccineViewModel.onEndDateChanged(it) }
                )
                Spacer(modifier = Modifier.height(16.dp))
                CustomButtomVaccine(
                    onClick = {
                        vaccineViewModel.getVaccineHistory(selectedDog ?: 0, startDate, endDate)
                    },
                    selectedDog = selectedDog,
                    selectedDates = selectedDates
                )
                Spacer(modifier = Modifier.height(8.dp))
                vaccineHistory?.forEach { vaccine ->
                    VaccineHistoryCard(vaccine = vaccine)
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeightChartVaccine(walkHistory: List<Walk>?, selectedDog: Int?, navController: NavController) {

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
                text = "Paseos",
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
                    Text("Ir a inicio")
                }

            }

            if (walkHistory == null) {

                Text(
                    text = "...",
                    style = MaterialTheme.typography.labelMedium
                )


            } else if (walkHistory.isEmpty()) {

                Text(
                    text = "No se tiene registro de paseos",
                    style = MaterialTheme.typography.labelMedium
                )

            } else {
//                Text(
//                    text = "Historial del peso:",
//                    style = MaterialTheme.typography.titleMedium
//                )
//                Spacer(modifier = Modifier.height(32.dp))

                val points = walkHistory.map { weight ->
                    LineChartData.Point(getDistance(weight.distance), weight.day )
                }

                val line = SolidLineDrawer(
                    color = MaterialTheme.colorScheme.primary
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "Distancia (km)",
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
                        labels = walkHistory.map { timestamptzFormatter.getFormattedDate(it.day) }
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

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BetweenDatesVaccine(
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
            .padding(start = 0.dp, end = 0.dp, top = 16.dp, bottom = 0.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
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
        TextField(
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
fun CustomButtomVaccine(
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
            enabled = selectedDog != null && selectedDates,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ver historial")
        }
    }
}

@Composable
fun VaccineHistoryCard(vaccine: Vaccine) {

    val timestamptzFormatter = TimestamptzFormatter()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 0.dp, end = 0.dp, top = 8.dp, bottom = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = vaccine.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Fecha:",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
//                    text = timestamptzFormatter.getFormattedDate(vaccine.date),
                    text = timestamptzFormatter.formattedDate(vaccine.date),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Proxima aplicación:",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = timestamptzFormatter.formattedDate(vaccine.next_due),
                    style = MaterialTheme.typography.bodyMedium
                )

            }
        }
    }
}

@Composable
fun NextVaccineCard(vaccine: Vaccine) {

    val timestamptzFormatter = TimestamptzFormatter()

//    Spacer(modifier = Modifier.height(16.dp))
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 0.dp, end = 0.dp, top = 16.dp, bottom = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = timestamptzFormatter.formattedDate(vaccine.next_due),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Última aplicación:",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = timestamptzFormatter.formattedDate(vaccine.date),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Vacuna aplicada:",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = vaccine.name,
                    style = MaterialTheme.typography.bodyMedium
                )

            }
        }
    }
}
