package com.example.userprofile.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.core.ui.components.DrawerContent
import com.example.core.ui.theme.PawpalTheme
import com.example.core.ui.components.TopAppBarPrimary
import com.example.userprofile.R
import com.example.userprofile.ui.models.SettingsOptionUserProfile
import com.example.userprofile.ui.viewmodel.UserProfileStateViewModel

@Preview(showBackground = true)
@Composable
fun UserProfileScreenPreview() {
    UserProfileScreen(
        navController = rememberNavController(),
        viewModel = UserProfileStateViewModel()
    )
}

@Composable
fun UserProfileScreen(navController: NavController, viewModel: UserProfileStateViewModel) {

    val name by viewModel.name.collectAsState()
    val biography by viewModel.biography.collectAsState()
    val avatarUrl by viewModel.avatarUrl.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    val options = listOf(
        SettingsOptionUserProfile(
            icon = R.drawable.profile,
            text = "Perfil",
            route = ({ navController.navigate("profile_screen") }),
        ),
        SettingsOptionUserProfile(
            icon = R.drawable.appareance,
            text = "Apariencia",
            route = ({ navController.navigate("appearance_screen") }),
        ),
//        SettingsOptionUserProfile(
//            icon = R.drawable.notification0,
//            text = "Notificaciones",
//            route = ({ navController.navigate("notification_settings_screen") }),
//        ),
        SettingsOptionUserProfile(
            icon = R.drawable.logout,
            text = "Cerrar sesión",
            route = ({
                navController.navigate("login") {
                    popUpTo(navController.graph.startDestinationId) { inclusive = true }
                    launchSingleTop = true


                }
            }),
        )
//        SettingsOptionUserProfile(
//            icon = R.drawable.profile,
//            text = "Visualización",
//            route = ({ navController.navigate("profile_screen") }),
//        )
    )

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
            topBar = { TopAppBarPrimary("Cuenta", drawerState, scope) }
        ) { innerPadding ->
            if (isLoading) {
                Box(Modifier.fillMaxSize()) {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
            } else {
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary)
                    ) {
                        Image(
                            painter = rememberImagePainter(
                                data = avatarUrl
                            ),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = name,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = biography,
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color.Gray,
                        textAlign = TextAlign.Center

                    )
//                        Spacer(modifier = Modifier.height(16.dp))
//                        Row(
//                            horizontalArrangement = Arrangement.SpaceEvenly,
//                            modifier = Modifier.fillMaxWidth()
//                        ) {
//                            ActionButton("Caninos")
//                            ActionButton("Notific.")
//                            ActionButton("Otros.")
//                        }
                    Spacer(modifier = Modifier.height(24.dp))
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                    ) {
                        items(options) { option ->
                            SettingOptionItem(option = option)
                        }
                    }
                }
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
//            .height(50.dp),
//        colors = ButtonColors(
//            containerColor = Color(0xFFC8E0B4),
//            contentColor = Color.Black,
//            disabledContainerColor = Color(0xFFF78058),
//            disabledContentColor = Color.White
//        )
//    ) {
//        Text(text)
//    }
//}

@Composable
fun SettingOptionItem(option: SettingsOptionUserProfile) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { option.route() })
            .clip(RoundedCornerShape(16.dp))
            .background(color = MaterialTheme.colorScheme.surface)
            .padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(
                        MaterialTheme.colorScheme.primary,
//                        Color(0xFFC8E0B4),
                        shape = CircleShape,
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Image(
                    painter = painterResource(option.icon),
                    contentDescription = "Perfil",
                    modifier = Modifier.size(40.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = option.text)
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.AutoMirrored.Default.ArrowForward, contentDescription = "Ir")
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
}