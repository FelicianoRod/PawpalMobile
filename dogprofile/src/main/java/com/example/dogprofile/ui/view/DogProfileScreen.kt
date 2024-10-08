package com.example.dogprofile.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.core.ui.components.DrawerContent
import com.example.core.ui.theme.PawpalTheme
//import com.example.core.ui.theme.PawpalTheme
//import com.example.core.ui.theme.PawpalTheme
//import com.example.core.ui.theme.PawpalTheme
//import com.example.dogprofile.ui.theme.PawpalTheme
//import com.example.dogprofile.ui.theme.PawpalTheme
import com.example.core.ui.components.TopAppBarPrimary
import com.example.dogprofile.domain.Dog
import com.example.dogprofile.ui.viewmodel.DogStateViewModel


@Preview(showBackground = true)
@Composable
fun DogProfileScreenPreview() {
//    DogProfileScreen(navController = rememberNavController(), viewModel = DogStateViewModel())
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
//fun DogProfileScreen(navController: NavController, viewModel: DogStateViewModel) {
fun DogProfileScreen(navController: NavController) {

    val viewModel: DogStateViewModel = DogStateViewModel()

    val isLoading by viewModel.isLoading.collectAsState()

    val dogList by viewModel.dogList.collectAsState()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

//    LaunchedEffect(Unit) {
    viewModel.getDogsUserList()
//    }

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
                topBar = { TopAppBarPrimary("Perrito", drawerState, scope) }
            ) { innerPadding ->
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                        .padding(16.dp),
                ) {
                    Text(
                        text = "Tus mascotas",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "Gestiona a tus amigos peludos con facilidad.",
                        style = MaterialTheme.typography.bodyMedium,
//                    color = MaterialTheme.colorScheme.primary
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Button(
                            onClick = { /*TODO*/ },
                            modifier = Modifier.width(140.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.secondary,
                                contentColor = MaterialTheme.colorScheme.onPrimary
                            )
                        ) {
                            Text(
                                text = dogList.size.toString() + " mascotas",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(
                            onClick = {
                                navController.navigate("add_dog")
                            },
                            modifier = Modifier.width(200.dp)
                        ) {
                            Icon(Icons.Default.Add, contentDescription = null)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Añadir mascota",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }

                    }
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        label = { Text("Buscar mascotas o dueños...") },
                        modifier = Modifier.fillMaxWidth(),
                        leadingIcon = {
                            Icon(
                                Icons.Default.Search,
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = Color.Gray,
                            cursorColor = MaterialTheme.colorScheme.primary
                        )

                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    if (isLoading) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center


                        ) {
                            CircularProgressIndicator(
//                                modifier = Modifier.width(64.dp),
                                color = MaterialTheme.colorScheme.primary
                            )

                        }
                    } else {

                        if (dogList.isEmpty()) {
                            Text(text = "No hay mascotas")
                        } else {

//                            LazyColumn {
//                                items(dogList) { dog ->
//                                    DogItem(dog)
//                                    Spacer(modifier = Modifier.padding(8.dp))
//                                }
//                            }
                            LazyVerticalGrid(
                                columns = GridCells.Fixed(2),
//                                contentPadding = PaddingValues(8.dp),
                                modifier = Modifier
//                                    .padding(innerPadding)
                                    .fillMaxSize()
                            ) {
                                items(dogList) { dog ->
                                    DogItem(dog, navController)
                                }
                            }

                        }

                    }
                }
            }
        }
    }
}

@Composable
fun DogItem(dog: Dog, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .clickable(onClick = {
                navController.navigate("dog_information")
            })
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = rememberImagePainter(
                    data = "https://static.fundacion-affinity.org/cdn/farfuture/PVbbIC-0M9y4fPbbCsdvAD8bcjjtbFc0NSP3lRwlWcE/mtime:1643275542/sites/default/files/los-10-sonidos-principales-del-perro.jpg"
                ),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
                    .height(120.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Text(text = dog.name ?: "No name")
//            Text(text = dog.owner_name ?: "No owner")
        }
    }
}

//@Composable
//fun ActionButton(text: String) {
//    Button(
//        onClick = { /* Handle button click */ },
//        shape = RoundedCornerShape(50),
//        modifier = Modifier
//            .padding(8.dp)
//            .height(50.dp)
////                .weight(1f)
//    ) {
//        Text(text)
//    }
//}

//@Composable
//fun SettingOptionItem(text: String) {
//    Card(
//        shape = RoundedCornerShape(16.dp),
////        modifier = Modifi
//        modifier = Modifier
//            .padding(9.dp)
//
//    ) {
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            modifier = Modifier.size(100.dp)
//                .background(
//                    Color(0xFFC8E0B4)
//                )
//                .padding(20.dp)
//        ) {
//            Icon(
//                imageVector = Icons.AutoMirrored.Filled.List,
//                contentDescription = null,
//                modifier = Modifier.size(30.dp)
//            )
////            Icon(Icons.Default.Settings)
//            Text(text)
//        }
//    }
//}