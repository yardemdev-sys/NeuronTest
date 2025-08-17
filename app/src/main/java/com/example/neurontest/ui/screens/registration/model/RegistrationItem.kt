package com.example.neurontest.ui.screens.registration.model

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.neurontest.R

@Composable
fun RegistrationItem(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String = "",
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    maxLength: Int = Int.MAX_VALUE,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    singleLine: Boolean = true,
    filter: (String) -> String = { it }
) {
    OutlinedTextField(
        value = value,
        onValueChange = {
            if (it.length <= maxLength) onValueChange(filter(it))
        },
        placeholder = { if (placeholder.isNotEmpty())
            Text(placeholder, color = MaterialTheme.colorScheme.onSecondary)
        },
        textStyle = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp),
        isError = isError,
        supportingText = {
            if (isError) {
                Text(
                    stringResource(R.string.invalid_input),
                    fontSize = 12.sp
                )
            } else {
                Text(
                    label,
                    color = MaterialTheme.colorScheme.onSecondary,
                    fontSize = 12.sp
                )
            }
        },
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        singleLine = singleLine,
        modifier = Modifier.defaultMinSize(minHeight = 70.dp).fillMaxWidth(),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surfaceContainerHighest,
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainerHighest,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(16.dp),
    )
}
