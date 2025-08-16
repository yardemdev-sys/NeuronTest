package com.example.neurontest.ui.screens.settings.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.neurontest.R
import com.example.neurontest.ui.screens.settings.model.SettingsUiState

@Composable
internal fun UserHeader(
    state: SettingsUiState,
    onNameChange: (String, String) -> Unit,
    onSaveClick: (String, String) -> Unit
) {
    var isEditing by remember { mutableStateOf(false) }
    var prevFirst by remember { mutableStateOf("") }
    var prevLast  by remember { mutableStateOf("") }

    if (state.isNameLoadError) {
        Text(
            text = stringResource(R.string.load_error),
            fontSize = 28.sp
        )
    } else if (state.isNameLoading) {
        CircularProgressIndicator()
    } else if (state.isAuth) {
        Row(verticalAlignment = Alignment.Bottom) {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                if (isEditing) {
                    BasicTextField(
                        value = state.firstName,
                        onValueChange = { onNameChange(it, state.lastName) },
                        textStyle = TextStyle(
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                            textDecoration = TextDecoration.Underline
                        ),
                        singleLine = true
                    )
                    BasicTextField(
                        value = state.lastName,
                        onValueChange = { onNameChange(state.firstName, it) },
                        textStyle = TextStyle(
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                            textDecoration = TextDecoration.Underline
                        ),
                        singleLine = true
                    )
                } else {
                    Text(state.firstName, fontSize = 28.sp, fontWeight = FontWeight.Bold)
                    Text(state.lastName, fontSize = 28.sp, fontWeight = FontWeight.Bold)
                }
            }
            Spacer(Modifier.size(10.dp))
            IconButton(
                onClick = {
                    if (isEditing) {
                        val ok = state.firstName.isNotBlank() && state.lastName.isNotBlank()
                        if (ok) {
                            isEditing = false
                            onSaveClick(prevFirst, prevLast)
                        }
                    } else {
                        prevFirst = state.firstName
                        prevLast = state.lastName
                        isEditing = true
                    }
                },
                modifier = Modifier.size(30.dp)
            ) {
                Icon(
                    imageVector = if (isEditing) Icons.Default.Check else Icons.Default.Edit,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSecondary
                )
            }
        }

        Text(
            text = stringResource(state.phoneNumber),
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onSecondary
        )
    } else {
        Text(
            text = stringResource(R.string.unauthorised),
            fontSize = 28.sp
        )
    }
}

