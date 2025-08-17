package com.example.neurontest.ui.screens.settings.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.neurontest.R
import com.example.neurontest.ui.components.ReturnButton
import com.example.neurontest.ui.screens.settings.model.SettingsUiState

@Composable
internal fun SettingsContent(
    state: SettingsUiState,
    onChangeName: (String, String) -> Unit,
    onNameSave: (String, String) -> Unit,
    onBack: () -> Unit,
    onPurchases: () -> Unit,
    onEmail: () -> Unit,
    onBiometricToggle: (Boolean) -> Unit,
    onChangeCode: () -> Unit,
    onRegister: () -> Unit,
    onLanguage: () -> Unit,
    onLogout: () -> Unit
) {
    val scroll = rememberScrollState()
    Column(
        modifier = Modifier
            .systemBarsPadding()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            // Для маленьких экранов
            .verticalScroll(scroll),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        ReturnButton(onClick = onBack)

        Spacer(Modifier.size(20.dp))

        UserHeader(state, onChangeName, onNameSave)

        if (state.isAuth) {
            SectionTitle(text = stringResource(R.string.my_purchases))
            ImageRow(
                image = R.drawable.purchases_image,
                right = { Arrow() },
                onClick = { onPurchases }
            )
            Spacer(Modifier.size(10.dp))
        }

        SectionTitle(text = stringResource(R.string.settings))

        if (state.isAuth) {
            ArrowRow(
                title = stringResource(R.string.email),
                right = {
                    Column {
                        Text(
                            text = stringResource(R.string.email_placeholder),
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                        Text(
                            text = stringResource(R.string.confirmation_required),
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                    Arrow()
                },
                onClick = onEmail
            )

            SwitchRow(
                title = stringResource(R.string.biometrical_auth),
                checked = state.isBiometricalAuthEnabled,
                onCheckedChange = onBiometricToggle
            )

            ArrowRow(
                title = stringResource(R.string.change_code),
                right = { Arrow() },
                onClick = onChangeCode
            )
        }

        if (!state.isAuth) {
            ArrowRow(
                title = stringResource(R.string.clients_registration),
                right = { Arrow() },
                onClick = onRegister
            )
        } else {
            ArrowRow(
                title = stringResource(R.string.logout),
                right = { Arrow() },
                onClick = onLogout
            )
        }

        ArrowRow(
            title = stringResource(R.string.language),
            right = {
                Text(
                    text = stringResource(R.string.russian),
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Arrow()
            },
            onClick = onLanguage
        )
    }
}
