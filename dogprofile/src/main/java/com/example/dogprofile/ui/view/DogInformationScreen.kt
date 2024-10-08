package com.example.dogprofile.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.core.ui.components.DrawerContent
import com.example.core.ui.components.TopAppBarSecondary
import com.example.dogprofile.ui.viewmodel.DogInformationViewModel

@Composable
fun DogInformationScreen(navController: NavController, viewModel: DogInformationViewModel) {

    Scaffold(
        topBar = { TopAppBarSecondary("mascota", navController) },
//            snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(state = rememberScrollState())
                .padding(16.dp),
        ) {
            AsyncImage(
                model = "https://www.maestrosdelweb.com/images/2009/08/crayones_jpg.jpg",
                contentDescription = "Imagen de ejemplo"

            )

        }
    }
}
