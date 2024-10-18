package com.example.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Output
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Preview(showBackground = true)
@Composable
fun DrawerPreview() {
    DrawerContent(navController = rememberNavController())
}

@Composable
fun DrawerContent(navController: NavController) {

    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    Box(
        modifier = Modifier.fillMaxSize().padding(12.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.TopStart)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Perfil", modifier = Modifier.padding(start = 16.dp))
            Spacer(modifier = Modifier.height(16.dp))
            Column() {
                DrawerItem(
                    icon = Icons.Default.Person,
                    label = "Usuarios",
                    notificationCount = 0,
                    selected = currentRoute == "user_profile",
                    modifier = Modifier.clickable {
                        navController.navigate("user_profile")
                    }
                )
                DrawerItem(
                    icon = Icons.Default.Pets,
                    label = "Mascotas",
                    notificationCount = 0,
                    selected = currentRoute == "dog_profile",
                    modifier = Modifier.clickable {
                        navController.navigate("dog_profile")
                    }
                )
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
        Column(
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Divider(
                color = Color.Gray,
                thickness = 1.dp,
                modifier = Modifier.padding(horizontal = 16.dp),
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Salir", modifier = Modifier.padding(start = 16.dp))
            DrawerItem(
                icon = Icons.Default.Output,
                label = "Salir",
                notificationCount = 0,
                selected = currentRoute == "login",
                modifier = Modifier.clickable {
                    navController.navigate("login") {
                        popUpTo(navController.graph.startDestinationId) { inclusive = true}
                        launchSingleTop = true

                    }
                }
            )
        }
    }
}

@Composable
fun DrawerItem(
    icon: ImageVector,
    label: String,
    notificationCount: Int,
    selected: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                if (selected) MaterialTheme.colorScheme.primary.copy(alpha = 0.90f)
                else MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(30.dp)
            )
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = if (selected) Color.Black else MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = label,
            color = if (selected) Color.Black else MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = if (notificationCount != 0) "$notificationCount" else "",
        )
    }
}
