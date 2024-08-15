package com.example.userprofile.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarUserProfile() {

    TopAppBar(
        colors = topAppBarColors(
            containerColor = Color(0xFFC8E0B4),
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text("")
        },
        navigationIcon = {
            IconButton(onClick = {
//                scope.launch {
//                    drawerState.apply {
//                        if (isClosed)  open() else close()
//                    }
//                }
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Atrás"
                )
            }
        },
    )
}