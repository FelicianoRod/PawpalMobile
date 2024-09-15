package com.example.dogprofile.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.CallMissedOutgoing
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.core.ui.theme.PawpalTheme
//import com.example.core.ui.theme.PawpalTheme
//import com.example.core.ui.theme.PawpalTheme
//import com.example.core.ui.theme.PawpalTheme
//import com.example.dogprofile.ui.theme.PawpalTheme
//import com.example.dogprofile.ui.theme.PawpalTheme
import com.example.userprofile.ui.components.TopAppBarUserProfile


@Preview(showBackground = true)
@Composable
fun DogProfileScreenPreview() {
    DogProfileScreen(navController = rememberNavController())
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun DogProfileScreen(navController: NavController) {
    PawpalTheme {
        Scaffold(
            topBar = { TopAppBarUserProfile() }
        ) { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding)
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
                            text = "2 Mascotas",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = { /*TODO*/ },
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
            }
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