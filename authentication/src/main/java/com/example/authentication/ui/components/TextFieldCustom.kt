package com.example.authentication.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TextFieldCustom(
    value: String,
    onValueChanged: (String) -> Unit,
    label: String = "",
    isError: Boolean = false,
//    supportingText: String = "",
    supportingText: List<String>,
//    onTextFieldChanged: (String) -> Unit
) {
    TextField(
        value = value,
        onValueChange = onValueChanged,
        label = { Text(text = label) },
        modifier = Modifier.fillMaxWidth(),
        isError = isError,
//        supportingText = { Text(text = supportingText) }
        supportingText = {
            Column {
                supportingText.forEach { error ->
                    Text(text = error)
                }
            }
        }
    )
}