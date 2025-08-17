package com.example.neurontest.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable


private val LightColorScheme = lightColorScheme(
    primary = White,
    onSurface = White,
    secondary = PurpleGrey40,
    onSecondary = LightGray,
    tertiary = Pink40,
    surface = Purple60,
    surfaceContainerHighest = Purple30,
    outline = Black
)

@Composable
fun NeuronTestTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}