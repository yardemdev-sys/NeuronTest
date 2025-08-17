package com.example.neurontest.ui.screens.registration.components

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.neurontest.R
import com.example.neurontest.ui.components.ReturnButton
import com.example.neurontest.ui.screens.registration.model.RegistrationItem
import com.example.neurontest.ui.screens.registration.model.RegistrationUiState

@Composable
internal fun RegistrationContent(
    state: RegistrationUiState,
    onNumberChange: (String) -> Unit,
    onCodeChange: (String) -> Unit,
    onFirstNameChange: (String) -> Unit,
    onLastNameChange: (String) -> Unit,
    onContinue: () -> Unit,
    onBack: () -> Unit
) {
    val scroll = rememberScrollState()
    val context = LocalContext.current

    Column(
        modifier = Modifier
        .systemBarsPadding()
        .padding(horizontal = 16.dp, vertical = 4.dp)
        .verticalScroll(scroll),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        ReturnButton { onBack() }

        Spacer(Modifier.size(20.dp))

        Text(
            text = stringResource(R.string.clients_registration),
            fontSize = 32.sp,
            style = MaterialTheme.typography.bodyLarge.copy(
                lineHeight = 40.sp,
            ),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.width(300.dp)
        )

        Spacer(modifier = Modifier.size(12.dp))

        RegistrationItem(
            value = state.bankNumber,
            onValueChange = onNumberChange,
            label = stringResource(R.string.number_supporting_text),
            placeholder = stringResource(R.string.number_placeholder),
            isError = state.isBankNumberError,
            maxLength = 16,
            visualTransformation = CardNumberTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )

        RegistrationItem(
            value = state.code,
            onValueChange = onCodeChange,
            label = stringResource(R.string.code_supporting_text),
            placeholder = stringResource(R.string.code_placeholder),
            isError = state.isCodeError,
            // Случайное ограничение, так как в тз оно не задано
            maxLength = 8,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            filter = { input -> input.filter(Char::isDigit)}
        )

        RegistrationItem(
            value = state.firstName,
            onValueChange = onFirstNameChange,
            label = stringResource(R.string.first_name_supporting_text),
            placeholder = stringResource(R.string.first_name_placeholder),
            isError = state.isFirstNameError,
            filter = { input -> input.replace(Regex("[^A-Za-z]"), "") }
        )

        RegistrationItem(
            value = state.lastName,
            onValueChange = onLastNameChange,
            label = stringResource(R.string.last_name_supporting_text),
            placeholder = stringResource(R.string.last_name_placeholder),
            isError = state.isLastNameError,
            filter = { input -> input.replace(Regex("[^A-Za-z]"), "") }
        )

        Spacer(modifier = Modifier.weight(1f))

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            TermsText {
                Toast.makeText(
                    context,
                    R.string.terms_placeholder,
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        Button(
            onClick = { onContinue() },
            enabled = state.isContinueButtonActive,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red,
                disabledContainerColor = MaterialTheme.colorScheme.error
            ),
            modifier = Modifier.fillMaxWidth().height(60.dp)
        ) {
            Text(
                text = stringResource(R.string.cont),
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        Spacer(modifier = Modifier.size(10.dp))
    }
}
