package com.example.userprofile.ui.view

//import androidx.compose.foundation.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
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
import com.example.userprofile.ui.components.TopAppBarUserProfile
import com.example.userprofile.ui.models.SettingsOptionUserProfile
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@Composable
fun UserProfileScreenPreview() {
    UserProfileScreen()
}

@Composable
fun UserProfileScreen() {
    val options = listOf(
        SettingsOptionUserProfile(icon = { Icon(Icons.AutoMirrored.Filled.List, contentDescription = null)}, text = "Editar perfil"),
        SettingsOptionUserProfile(icon = { Icon(Icons.AutoMirrored.Filled.List, contentDescription = null)}, text = "Mi estatus"),
        SettingsOptionUserProfile(icon = { Icon(Icons.AutoMirrored.Filled.List, contentDescription = null)}, text = "Ajustes"),
        SettingsOptionUserProfile(icon = { Icon(Icons.AutoMirrored.Filled.List, contentDescription = null)}, text = "Invitar a un amigo"),
        SettingsOptionUserProfile(icon = { Icon(Icons.AutoMirrored.Filled.List, contentDescription = null)}, text = "Ayuda"),
    )

    Scaffold(
        topBar = { TopAppBarUserProfile() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
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
                text = "Feliciano Rodríguez",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Amante de los perritos",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                ActionButton("5 Min")
                ActionButton("Message")
                ActionButton("Location")
            }

            Spacer(modifier = Modifier.height(24.dp))

//            Column() {
//                options.forEach { option ->
//                    SettingOptionItem(option = option)
//                }
//            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Gray)
                    .padding(8.dp),

            ) {
                items(options) { option ->
                    SettingOptionItem(option = option)
                }


            }
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
fun SettingOptionItem(option: SettingsOptionUserProfile) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center,
                ) {
                option.icon()
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = option.text)

        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.AutoMirrored.Default.ArrowForward, contentDescription = "Ir" )
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
}