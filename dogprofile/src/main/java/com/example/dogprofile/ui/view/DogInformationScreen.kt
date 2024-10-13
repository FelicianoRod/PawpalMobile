package com.example.dogprofile.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.SubcomposeAsyncImage
import com.example.core.ui.components.TopAppBarSecondary
import com.example.dogprofile.data.supabase.DogRepositoryImpl
import com.example.dogprofile.ui.viewmodel.DogInformationViewModel

@Preview(showBackground = true)
@Composable
fun DogInformationScreenPreview() {
    DogInformationScreen(navController = rememberNavController(), viewModel = DogInformationViewModel(dogRepository = DogRepositoryImpl()))
}

@Composable
fun DogInformationScreen(navController: NavController, viewModel: DogInformationViewModel) {

    val dogInformation by viewModel.dogInformation.collectAsState()

    Scaffold(
        topBar = { TopAppBarSecondary("mascota", navController) },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(state = rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(48.dp))
            Box(
                modifier = Modifier
                    .size(250.dp)
                    .clip(CircleShape),
                contentAlignment = Alignment.Center,

            ) {
                SubcomposeAsyncImage(
                    model = dogInformation?.image_url,
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
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = dogInformation?.name ?: "",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(48.dp))
            Text(
                text = "Fecha de nacimiento",
                style = MaterialTheme.typography.labelSmall,
                color = Color.Gray
            )
            Text(
                text = dogInformation?.birthdate ?: "",
                style = MaterialTheme.typography.bodyMedium,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Raza",
                style = MaterialTheme.typography.labelSmall,
                color = Color.Gray
            )
            Text(
                text = dogInformation?.breeds?.name ?: "",
                style = MaterialTheme.typography.bodyMedium,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Dueño de la mascota",
                style = MaterialTheme.typography.labelSmall,
                color = Color.Gray
            )
            Text(
                text = dogInformation?.profiles?.full_name ?: "",
                style = MaterialTheme.typography.bodyMedium,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Ciudad",
                style = MaterialTheme.typography.labelSmall,
                color = Color.Gray
            )
            Text(
                text = dogInformation?.profiles?.city ?: "",
                style = MaterialTheme.typography.bodyMedium,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Descripción",
                style = MaterialTheme.typography.labelSmall,
                color = Color.Gray
            )
            Text(
                text = dogInformation?.description ?: "No se ha registrado una descripción",
                style = MaterialTheme.typography.bodyMedium,
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}