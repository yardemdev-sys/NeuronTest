package com.example.neurontest.ui.screens.settings.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.neurontest.R
import com.example.neurontest.ui.components.ReturnButton
import com.example.neurontest.ui.screens.settings.SettingsViewModel
import com.example.neurontest.ui.screens.settings.model.SettingsIntent
import org.koin.androidx.compose.koinViewModel

@Composable
fun Settings(
    onNavigateRegister: () -> Unit,
    onBack: () -> Unit,
    vm: SettingsViewModel = koinViewModel()
) {
    val uiState = vm.uiState.collectAsState()
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .systemBarsPadding(),
        verticalArrangement = Arrangement.spacedBy(10.dp)

    ) {
        ReturnButton { onBack }

        Spacer(modifier = Modifier.size(20.dp))

        if (uiState.value.isAuth) {
            Text(
                text = uiState.value.firstName!!,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = uiState.value.lastName!!,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = stringResource(uiState.value.phoneNumber),
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onSecondary
            )

        } else {
            Text(
                text = stringResource(R.string.unauthorised),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
        }

        if (uiState.value.isAuth) {
            Text(
                text = stringResource(R.string.my_purchases),
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onSecondary
            )
            SettingItem(
                leftSlot = {},
                rightSlot = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = null
                    )
                },
                onClick = { /* TODO: навигация к покупкам */ }
            )
            Spacer(modifier = Modifier.size(14.dp))
        }

        Text(
            text = stringResource(R.string.settings),
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onSecondary
        )

        if (uiState.value.isAuth) {
            SettingItem(
                leftSlot = {
                    Text(
                        text = stringResource(R.string.email),
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                },
                rightSlot = {
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
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = null
                    )
                },
                onClick = { /* TODO: навигация к покупкам */ }
            )
            SettingItem(
                leftSlot = {
                    Text(
                        text = stringResource(R.string.biometrical_auth),
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                },
                rightSlot = {
                    Switch(
                        checked = uiState.value.isBiometricalAuthEnabled,
                        onCheckedChange = {
                            vm.performIntent(SettingsIntent.BiometricalAuthToggle(
                                !uiState.value.isBiometricalAuthEnabled
                            ))
                        },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White,
                            checkedTrackColor = Color.Red,
                            uncheckedThumbColor = Color.DarkGray,
                            uncheckedTrackColor = Color.Gray,
                            uncheckedBorderColor = Color.Transparent
                        )
                    )
                },
                onClick = {}
            )
            SettingItem(
                leftSlot = {
                    Text(
                        text = stringResource(R.string.change_code),
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                },
                rightSlot = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = null
                    )
                },
                onClick = {}
            )
        }

        SettingItem(
            leftSlot = {
                Text(
                    text = stringResource(R.string.clients_registration),
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            },
            rightSlot = {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = null
                )
            },
            onClick = {
                onNavigateRegister()
            }
        )
        SettingItem(
            leftSlot = {
                Text(
                    text = stringResource(R.string.language),
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            },
            rightSlot = {
                Text(
                    text = stringResource(R.string.russian),
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onPrimary
                )

                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = null
                )
            },
            onClick = {}
        )
    }
}
