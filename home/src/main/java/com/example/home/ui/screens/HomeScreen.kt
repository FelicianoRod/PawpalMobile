package com.example.home.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.core.ui.components.DrawerContent
import com.example.core.ui.components.TopAppBarPrimary
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val pets = listOf("Jack", "Oddy", "Spike", "Moon", "Bella", "Max")
    var selectedPet by remember { mutableStateOf<String?>(null) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerContent(navController)
            }
        },
    ){
        Scaffold(
            topBar = { TopAppBarPrimary("Hola, NombreDeUsuario", drawerState, scope)
//                TopAppBar(
//                    colors = topAppBarColors(
//                        containerColor = Color(0xFFC8E0B4),
//                        titleContentColor = MaterialTheme.colorScheme.primary,
//                    ),
//                    title = {
//                        Text("Inicio")
//                    },
//                    navigationIcon = {
//                        IconButton(onClick = {
//                            scope.launch {
//                                drawerState.apply {
//                                    if (isClosed)  open() else close()
//                                }
//                            }
//                        }) {
//                            Icon(
//                                imageVector = Icons.AutoMirrored.Filled.List,
//                                contentDescription = "Atrás"
//                            )
//                        }
//                    },
//                )
            }
        ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "Your Pets", style = MaterialTheme.typography.titleLarge)

                Spacer(modifier = Modifier.height(16.dp))

                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(pets) { pet ->
                        PetItem(
                            name = pet,
                            isSelected = pet == selectedPet,
                            onClick = { selectedPet = pet }
                        )
                    }
                }
            }

//            Text(
//                modifier = Modifier.padding(8.dp),
//                text = """Organización: Dividen tu código en componentes lógicos más pequeños y manejables. En lugar de tener todo el código en un solo módulo gigante, puedes separarlo por funcionalidades (ej: :feature:home, :feature:profile), capas de arquitectura (ej: :data, :domain, :ui) o cualquier otra estructura que se adapte a tu proyecto.""".trimIndent(),
//            )
        }

        }
    }
//    var presses by remember { mutableStateOf(0)}
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                colors = topAppBarColors(
//                    containerColor = MaterialTheme.colorScheme.primaryContainer,
//                    titleContentColor = MaterialTheme.colorScheme.primary,
//                ),
//                title = {
//                    Text("Inicio")
//                },
//                navigationIcon = {
//                    IconButton(onClick = { /*TODO*/ }) {
//                        Icon(
//                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
//                            contentDescription = "Atrás"
//                        )
//                    }
//                },
//                actions = {
//                    IconButton(onClick  = { /*TODO*/ }) {
//                        Icon(
//                            imageVector = Icons.Filled.Menu,
//                            contentDescription = "Menú"
//                        )
//                    }
//                }
//            )
//        },
//        bottomBar = {
//            BottomAppBar(
//                containerColor = MaterialTheme.colorScheme.primaryContainer,
//                contentColor = MaterialTheme.colorScheme.primary,
//            ) {
//                Text(
//                    modifier = Modifier.fillMaxSize(),
//                    textAlign = TextAlign.Center,
//                    text = "Navegación",
//                )
//            }
//        },
//        floatingActionButton = {
//            SmallFloatingActionButton(
//                onClick = { presses++ }
//            ) {
//                Icon(Icons.Default.Add, contentDescription = "Añadir")
//            }
//        },
//        drawerContent = {
//            Text(text = "Drawer")
//        }
//    ) { innerPadding ->
//        Column(
//            modifier = Modifier
//                .padding(innerPadding),
//            verticalArrangement = Arrangement.spacedBy(16.dp),
//        ) {
//            Text(
//                modifier = Modifier.padding(8.dp),
//                text = """Organización: Dividen tu código en componentes lógicos más pequeños y manejables. En lugar de tener todo el código en un solo módulo gigante, puedes separarlo por funcionalidades (ej: :feature:home, :feature:profile), capas de arquitectura (ej: :data, :domain, :ui) o cualquier otra estructura que se adapte a tu proyecto.""".trimIndent(),
//            )
//        }
//
//    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    HomeScreen(navController = rememberNavController())
}

@Composable
fun PetItem(name: String, isSelected: Boolean, onClick: () -> Unit) {
    val backgroundColor = if (isSelected) MaterialTheme.colorScheme.primary else Color.LightGray
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
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = name, fontSize = 14.sp)
    }
}