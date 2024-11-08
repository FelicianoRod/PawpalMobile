package com.example.home.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.SubcomposeAsyncImage
import com.example.core.data.supabase
import com.example.core.ui.components.DrawerContent
import com.example.core.ui.components.TopAppBarPrimary
import com.example.core.ui.repository.StateRepository
import com.example.core.utils.dayMonth
import com.example.core.utils.getDistance
import com.example.home.data.repository.HomeRepositoryImpl
import com.example.home.data.repository.WeightRepositoryImpl
import com.example.home.domain.model.Dog
import com.example.home.domain.model.Nutrition
import com.example.home.domain.model.Walk
import com.example.home.domain.model.Weight
import com.example.home.ui.viewmodel.HomeViewModel
import com.example.home.ui.viewmodel.NutritionViewModel
import com.example.home.ui.viewmodel.WalkViewModel
import com.example.home.ui.viewmodel.WeightViewModel
import com.github.tehras.charts.bar.BarChart
import com.github.tehras.charts.bar.BarChartData
import com.github.tehras.charts.bar.renderer.label.LabelDrawer
import com.github.tehras.charts.bar.renderer.label.SimpleValueDrawer
import com.github.tehras.charts.bar.renderer.xaxis.XAxisDrawer
import com.github.tehras.charts.line.LineChart
import com.github.tehras.charts.line.LineChartData
import com.github.tehras.charts.line.renderer.line.SolidLineDrawer
import com.github.tehras.charts.line.renderer.point.FilledCircularPointDrawer
import com.github.tehras.charts.piechart.animation.simpleChartAnimation
import io.github.jan.supabase.gotrue.auth

@Composable
@Preview
fun HomeScreenPreview() {
    HomeScreen(
        navController = rememberNavController(),
        viewModel = HomeViewModel(HomeRepositoryImpl(), StateRepository()),
        weightViewModel = WeightViewModel(WeightRepositoryImpl())
    )
}

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
    weightViewModel: WeightViewModel = hiltViewModel(),
    walkViewModel: WalkViewModel = hiltViewModel(),
    nutritionViewModel: NutritionViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit) {
        viewModel.getDogs()
    }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val dogs by viewModel.dogs.collectAsState()
    var selectedDog by remember { mutableStateOf<Dog?>(null) }

    val weightHistory by weightViewModel.weightHistory.collectAsState()
    val isLoadingWeightHistory by weightViewModel.isLoading.collectAsState()

    val walks by walkViewModel.walks.collectAsState()
    val isLoadingWalks by walkViewModel.isLoading.collectAsState()

    val nutritionHistory by nutritionViewModel.nutritionHistory.collectAsState()
    val isLoadingNutritionHistory by nutritionViewModel.isLoading.collectAsState()

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
                TopAppBarPrimary("Inicio", drawerState, scope)
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState()),
//                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp, 8.dp)
                ) {
                    Text(text = "Tus mascotas", style = MaterialTheme.typography.titleLarge)

                    Spacer(modifier = Modifier.height(16.dp))

                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(dogs ?: emptyList()) { dog ->
                            PetItem(
                                dog = dog,
                                isSelected = dog == selectedDog,
                                onClick = {
                                    selectedDog = dog
                                    weightViewModel.getWeightHistory(dog.id)
                                    walkViewModel.getWalks(dog.id)
                                    nutritionViewModel.getNutritionHistory(dog.id)

                                }
                            )
                        }
                    }
                }
                WeightChart(weightHistory)
                WalksChart(walks)
                NutritionChart(nutritionHistory)
            }
        }
    }
}

@Composable
fun NutritionChart(nutritionHistory: List<Nutrition>?) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp, 0.dp)
        ) {
            Text(
                text = "Alimentación",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(16.dp)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (nutritionHistory == null) {

                Text(
                    text = "Elige una mascota para ver su alimentación",
                    style = MaterialTheme.typography.labelMedium
                )

            } else if (nutritionHistory.isEmpty()) {

                Text(
                    text = "No se tiene registros de alimentación",
                    style = MaterialTheme.typography.labelMedium
                )
            } else {

                val bars = nutritionHistory.map { nutrition ->
                    BarChartData.Bar(
                        label = dayMonth(nutrition.created_at),
                        value = nutrition.food_amount.toFloat(),
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                BarChart(
                    barChartData = BarChartData(bars = bars),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(16.dp),
                    animation = simpleChartAnimation(),
                    labelDrawer = SimpleValueDrawer(
                        drawLocation =  SimpleValueDrawer.DrawLocation.XAxis
                    )
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun WalksChart(walks: List<Walk>?) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp, 0.dp)
        ) {
            Text(
                text = "Actividad de paseo",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(16.dp)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (walks == null) {

                Text(
                    text = "Elige una mascota para ver sus paseos",
                    style = MaterialTheme.typography.labelMedium
                )

            } else if (walks.isEmpty()) {

                Text(
                    text = "No se tiene registros de paseos",
                    style = MaterialTheme.typography.labelMedium
                )
            }
            else {
                val bars = walks.map { walk ->
                    BarChartData.Bar(
                        label = dayMonth(walk.day),
                        value = getDistance(walk.distance),
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                BarChart(
                    barChartData = BarChartData(bars = bars),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(16.dp),
                    animation = simpleChartAnimation(),
                    labelDrawer = SimpleValueDrawer(
                        drawLocation =  SimpleValueDrawer.DrawLocation.XAxis
                    ),
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun PetItem(dog: Dog, isSelected: Boolean, onClick: () -> Unit) {
    val backgroundColor = if (isSelected) Color.LightGray else Color.LightGray
    val borderColor = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(onClick = onClick)
    ) {
        Box(
            modifier = Modifier
                .size(72.dp)
                .clip(CircleShape)
                .background(backgroundColor)
                .border(
                    width = 3.dp,
                    color = borderColor,
                    shape = CircleShape
                )
        ) {
            SubcomposeAsyncImage(
                model = dog.image_url,
                contentDescription = "Dog Image",
                contentScale = ContentScale.Crop,
                loading = {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                },
                error = {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "No hay foto",
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = dog.name, fontSize = 14.sp)
    }
}

@Composable
fun WeightChart(weightHistory: List<Weight>?) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp, 0.dp)
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
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            if (weightHistory == null) {

                Text(
                    text = "Elige una mascota para ver el historial de su peso",
                    style = MaterialTheme.typography.labelMedium
                )

            } else if (weightHistory.isEmpty()) {

                Text(
                    text = "No se tiene registro de peso",
                    style = MaterialTheme.typography.labelMedium
                )

            } else {

                Text(
                    text = "Peso en gramos:",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(8.dp))

                // Convertir `weightHistory` en una lista de puntos para el gráfico
                val points = weightHistory.map { weight ->
                    LineChartData.Point(weight.weight.toFloat(), weight.created_at )
                }

                val line = SolidLineDrawer(
                    color = MaterialTheme.colorScheme.primary
                )

                // Crear el gráfico de líneas
                LineChart(
                    linesChartData = listOf(LineChartData(points = points, lineDrawer = line)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(16.dp),
                    animation = simpleChartAnimation(),
                    pointDrawer = FilledCircularPointDrawer(color = MaterialTheme.colorScheme.secondary),
//        lineDrawer = SolidLineDrawer(),
//        xAxisDrawer = SimpleXAxisDrawer(),
//        yAxisDrawer = SimpleYAxisDrawer(),
                    horizontalOffset = 5f,
                    labels = weightHistory.map { dayMonth(it.created_at) } // Etiquetas para el eje X
                )

//                Spacer(modifier = Modifier.height(8.dp))
//
//                Text(
//                    text = "Gráfico de peso en los últimos 12 meses",
//                    style = MaterialTheme.typography.labelMedium
//                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}