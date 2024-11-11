package com.example.weight.ui.screens

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.core.ui.components.DrawerContent
import com.example.core.ui.components.TopAppBarPrimary
import com.example.core.ui.viewmodel.DrawerViewModel

@Composable
fun WeightScreen(
    navController: NavController,
    drawerViewModel: DrawerViewModel = hiltViewModel(),
) {

    val drawerState = drawerViewModel.drawerState.value
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = rememberDrawerState(initialValue = drawerState),
        drawerContent = {
            ModalDrawerSheet {
                DrawerContent(navController)
            }
        },
    ) {
        Scaffold(
            topBar = {
                TopAppBarPrimary("Inicio", DrawerState(DrawerValue.Closed), scope)
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

            }
        }
    }
}