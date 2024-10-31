package com.example.dogprofile.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.SubcomposeAsyncImage
import com.example.core.ui.components.DrawerContent
import com.example.core.ui.theme.PawpalTheme
import com.example.core.ui.components.TopAppBarPrimary
import com.example.dogprofile.data.supabase.DogRepositoryImpl
import com.example.dogprofile.domain.models.Dog
import com.example.dogprofile.domain.models.Tag
import com.example.dogprofile.ui.viewmodel.DogProfileViewModel
import kotlin.random.Random

@Preview(showBackground = true)
@Composable
fun DogProfileScreenPreview() {
    DogProfileScreen(
        navController = rememberNavController(),
        viewModel = DogProfileViewModel(dogRepository = DogRepositoryImpl())
    )
}

//@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun DogProfileScreen(navController: NavController, viewModel: DogProfileViewModel = hiltViewModel()) {

    viewModel.getDogsUserList()

    val isLoading by viewModel.isLoading.collectAsState()
    val dogList by viewModel.dogList.collectAsState()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerContent(navController)
            }
        },
    ) {
        Scaffold(
            topBar = { TopAppBarPrimary("Tus mascotas", drawerState, scope) },
            floatingActionButton = { AddDogButton(navController) }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(16.dp),
            ) {
                Text(
                    text = "Gestiona a tus amigos peludos con facilidad.",
                    style = MaterialTheme.typography.bodyMedium,
                )
                Spacer(modifier = Modifier.padding(8.dp))
                if (isLoading) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator(
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                } else {
                    if (dogList.isNullOrEmpty()) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "No hay mascotas registradas",
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.labelMedium
                            )
                        }
                    } else {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
//                                contentPadding = PaddingValues(8.dp),
                            modifier = Modifier
                                .fillMaxSize(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(dogList!!) { dog ->
                                DogItem(dog, navController)
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
            .clickable(onClick = {
                navController.navigate("dog_information/${dog.id}")
            })
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .height(150.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center,
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
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column(
                    modifier = Modifier.weight(5f)
                ) {
                    Spacer(modifier = Modifier.padding(8.dp))
                    Text(
                        text = dog.name,
                        style = MaterialTheme.typography.titleLarge,
                    )
                    Spacer(modifier = Modifier.padding(2.dp))
                    Text(
                        text = dog.breeds.name,
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.padding(4.dp))
                }
                Spacer(modifier = Modifier.width(4.dp))
                Column(
                    modifier = Modifier.weight(2f)
                ) {
                    Spacer(modifier = Modifier.padding(8.dp))
                    dog.tags?.forEach { tag ->
                        TagChip(tag)
                        Spacer(modifier = Modifier.padding(1.dp))
                    }
                }

            }
        }
    }
}

@Composable
fun AddDogButton(navController: NavController) {
    FloatingActionButton(
        onClick = {
            navController.navigate("add_dog")
        },
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Añadir canino",
        )
    }
}

@Composable
fun TagChip(tag: Tag) {
    // Generar un color aleatorio para cada chip
    val backgroundColor = Color(
        Random.nextFloat(),
        Random.nextFloat(),
        Random.nextFloat(),
        alpha = 0.3f // Ajuste de transparencia
    )

    Surface(
        shape = RoundedCornerShape(16.dp),
        color = backgroundColor,
    ) {
        Text(
            text = tag.text,
            modifier = Modifier
                .padding(horizontal = 4.dp),
            style = MaterialTheme.typography.labelSmall,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}