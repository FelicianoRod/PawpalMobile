package com.example.home.ui.partials

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
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Forum
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Phone
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
import androidx.navigation.compose.rememberNavController

//import java.lang.reflect.Modifier

@Preview(showBackground = true)
@Composable
fun DrawerPreview() {
    DrawerContent(navController = rememberNavController())
}

@Composable
fun DrawerContent(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize().padding(12.dp)
    ) {
        DrawerSection(navController)
    }
}

@Composable
fun DrawerSection(navController: NavController) {
    Column() {
        Spacer(modifier = Modifier.height(16.dp))
        Row() {
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = "Perfil")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column() {
            DrawerItem(
                icon = Icons.Default.Person,
                label = "Usuarios",
                notificationCount = 24,
                modifier = Modifier.clickable {
//                    scope.launch { drawerState.close() }
                    navController.navigate("user_profile")
                }
//                modifier = Modifier.clickable {
//                    scope.launch { drawerState.close() }
//                    navController.navigate(Screen.Home.route)
//                }
            )
            DrawerItem(
                icon = Icons.Default.Pets,
                label = "Mascotas",
                notificationCount = 1,
                modifier = Modifier.clickable {
//                    scope.launch { drawerState.close() }
                    navController.navigate("dog_profile")
                }
            )
            Divider(
                color = Color.Gray,
                thickness = 1.dp,
                modifier = Modifier.padding(horizontal = 16.dp),
            )
            Spacer(modifier = Modifier.height(32.dp))
//            Row() {
//                Spacer(modifier = Modifier.width(16.dp))
//                Text(text = "Comunidad")
//            }
//            Spacer(modifier = Modifier.height(16.dp))
//            DrawerItem(
//                icon = Icons.Default.Forum,
//                label = "Foro",
//                notificationCount = 1,
//            )
//            Divider(
//                color = Color.Gray,
//                thickness = 1.dp,
//                modifier = Modifier.padding(horizontal = 16.dp),
//            )
        }
    }
}

@Composable
fun DrawerItem(
    icon: ImageVector,
    label: String,
    notificationCount: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
//            .padding(horizontal = 16.dp, vertical = 8.dp)
//            .background(Color(0xFFEDE7F6), shape = RoundedCornerShape(24.dp))
            .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(30.dp))
            .padding(horizontal = 16.dp, vertical = 16.dp),


        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = label,
//            style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = notificationCount.toString(),
//            style = MaterialTheme.typography.body2
        )

    }
}
