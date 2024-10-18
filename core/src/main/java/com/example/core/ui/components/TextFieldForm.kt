package com.example.core.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldForm(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String,
    placeholder: String,
    supportingText: String = "",
    errorList: List<String> = listOf(),
    isError: Boolean = false,
    readOnly: Boolean = false,
    singleLine: Boolean = true,
    maxLines: Int = 1,
    minLines: Int = 1,
//    keyboardOptions: KeyboardOptions,
    keyboardType: KeyboardType = KeyboardType.Text,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    ) {
    TextField(
//        Text
        value = value,
        label = { Text(label)},
        placeholder = { Text(placeholder)},
        supportingText = {
            if (supportingText.isNotEmpty()) {
                Text(
                    text = supportingText,
                    color = Color.Black,
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Column {
                errorList.forEach { error ->
                    Text(text = error)
                }
            }
        },

//        Style
        modifier = modifier.fillMaxWidth(),
        enabled = true,
        textStyle = MaterialTheme.typography.bodyMedium,
        leadingIcon = null,
        trailingIcon = trailingIcon,
        prefix = null,
        suffix = null,
        isError = isError,
        visualTransformation = visualTransformation,
        singleLine = singleLine,
        maxLines = maxLines,
        minLines = minLines,
//        shape =
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.surface,
            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.secondary,
            errorIndicatorColor = Color.Red
        ),

//        fun
        onValueChange = onValueChange,
        readOnly = readOnly,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = ImeAction.Next
        ),
//        keyboardAction = KeyboardActions(
//
//        )
//        interactionSource = null,
    )
}