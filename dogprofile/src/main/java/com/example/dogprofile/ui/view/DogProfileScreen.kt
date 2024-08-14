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
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.dogprofile.ui.models.SettingOption


@Preview(showBackground = true)
@Composable
fun DogProfileScreenPreview() {
    DogProfileScreen()
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DogProfileScreen() {
    val items = listOf(
        SettingOption(icon = { Icon(Icons.AutoMirrored.Filled.List, contentDescription = null)}, text = "Inf. basica", onClick = {}),
        SettingOption(icon = { Icon(Icons.AutoMirrored.Filled.List, contentDescription = null)}, text = "Desparasitante", onClick = {}),
        SettingOption(icon = { Icon(Icons.AutoMirrored.Filled.List, contentDescription = null)}, text = "Veterinario", onClick = {}),
        SettingOption(icon = { Icon(Icons.AutoMirrored.Filled.List, contentDescription = null)}, text = "Otros", onClick = {}),
        SettingOption(icon = { Icon(Icons.AutoMirrored.Filled.List, contentDescription = null)}, text = "Option 2", onClick = {}),
        SettingOption(icon = { Icon(Icons.AutoMirrored.Filled.List, contentDescription = null)}, text = "Option 1", onClick = {}),
    )

    Scaffold(
//        topBar = { TopAppBarUserProfile() }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                Image(
                    painter = rememberImagePainter(data = "https://stickerly.pstatic.net/sticker_pack/YIkMXfxwRMMvVMIoXINUA/YPR0TM/2/b73fd3d3-800c-4a7b-babf-43eafb8b23c7.png"),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Camila",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Cruza chihuahua",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(32.dp))

            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Row(

                ) {
                    SettingOptionItem(text = "Inf. basica")
                    SettingOptionItem(text = "Desparasitante")
                    SettingOptionItem(text = "Veterinario")
                }
                Row() {
                    SettingOptionItem(text = "Otros")
                    SettingOptionItem(text = "Otros")
                }
                Row() {

                }

            }

//            Column() {
//                options.forEach { option ->
//                    SettingOptionItem(option = option)
//                }
//            }
//            FlowRow(
//                maxItemsInEachRow = 3,
//                modifier = Modifier.fillMaxSize(). background(Color.Gray),
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalArrangement = Arrangement.spacedBy(8.dp)
//            ) {
//                items.forEach { item ->
//                    SettingOptionItem(option = item)
//                }
//            }
//            LazyColumn(
//                for (i in 1..10) {
//                    Text("Item $i", modifier = Modifier.padding(8.dp))
//                }
//            }
        }

    }

}

@Composable
fun ActionButton(text: String) {
    Button(
        onClick = { /* Handle button click */ },
        shape = RoundedCornerShape(50),
        modifier = Modifier
            .padding(8.dp)
            .height(50.dp)
//                .weight(1f)
    ) {
        Text(text)
    }
}

@Composable
fun SettingOptionItem(text: String) {
    Card(
        shape = RoundedCornerShape(16.dp),
//        modifier = Modifi
        modifier = Modifier
            .padding(4.dp)

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.size(100.dp).padding(20.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.List,
                contentDescription = null,
                modifier = Modifier.size(30.dp)
            )
//            Icon(Icons.Default.Settings)
            Text(text)
        }
    }
}