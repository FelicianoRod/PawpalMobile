package com.example.authentication.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ButtonCustom(
    label: String,
    enabled: Boolean,
//    onSignUpSelected: () -> Unit
) {
    Button(
        onClick = { /*TODO*/ },
        enabled = enabled,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
    ) {
        Text(text = label)
    }
}