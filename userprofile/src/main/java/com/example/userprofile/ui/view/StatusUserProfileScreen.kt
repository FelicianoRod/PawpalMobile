package com.example.userprofile.ui.view
//
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.tooling.preview.Preview
//import com.example.userprofile.R
//
//@Preview(showBackground = true)
//@Composable
//fun StatusScreen() {
//    var currentStatus by remember { mutableStateOf("Feeling Great!") }
//    var profilePicture by remember { mutableStateOf(R.drawable.ic_launcher_foreground) }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//    ) {
//        Text(
//            text = "My Status",
//            fontSize = 24.sp,
//            fontWeight = FontWeight.Bold,
//            modifier = Modifier.padding(bottom = 16.dp)
//        )
//
//        Box(
//            modifier = Modifier
//                .size(120.dp)
//                .clip(CircleShape)
//                .border(2.dp, Color.Gray, CircleShape)
//                .align(Alignment.CenterHorizontally)
//        ) {
//            Image(
//                painter = painterResource(id = profilePicture),
//                contentDescription = null,
//                contentScale = ContentScale.Crop,
//                modifier = Modifier.fillMaxSize()
//            )
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Text(
//            text = "Current Status",
//            fontSize = 20.sp,
//            fontWeight = FontWeight.Medium,
//            modifier = Modifier.padding(vertical = 8.dp)
//        )
//
//        Text(
//            text = currentStatus,
//            fontSize = 18.sp,
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(Color.LightGray)
//                .padding(12.dp)
//                .align(Alignment.CenterHorizontally)
//        )
//
//        Spacer(modifier = Modifier.height(24.dp))
//
//        StatusOption(
//            icon = R.drawable.ic_mood,
//            text = "Update Mood",
//            onClick = { /* Lógica para actualizar el estado de ánimo */ }
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        StatusOption(
//            icon = R.drawable.ic_activity,
//            text = "Log Activity",
//            onClick = { /* Lógica para registrar actividad */ }
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        StatusOption(
//            icon = R.drawable.ic_sleep,
//            text = "Track Sleep",
//            onClick = { /* Lógica para rastrear el sueño */ }
//        )
//    }
//}
//
//@Composable
//fun StatusOption(icon: Int, text: String, onClick: () -> Unit) {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .clickable(onClick = onClick)
//            .padding(12.dp)
//            .border(1.dp, Color.Gray, shape = MaterialTheme.shapes.medium)
//            .padding(16.dp),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Image(
//            painter = painterResource(id = icon),
//            contentDescription = null,
//            modifier = Modifier.size(24.dp)
//        )
//
//        Spacer(modifier = Modifier.width(16.dp))
//
//        Text(
//            text = text,
//            fontSize = 18.sp
//        )
//    }
//}