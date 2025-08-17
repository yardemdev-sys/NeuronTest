package com.example.neurontest.ui.screens.settings.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun Arrow() {
    Icon(
        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
        contentDescription = null
    )
}


@Composable
internal fun ArrowRow(
    title: String?,
    right: @Composable RowScope.() -> Unit,
    onClick: () -> Unit
) {
    SettingItem(
        leftSlot = {
            title?.let {
                Text(
                    text = it,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }
        },
        rightSlot = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                content = right
            )
        },
        onClick = onClick
    )
}

@Composable
internal fun ImageRow(
    image: Int,
    right: @Composable () -> Unit,
    onClick: () -> Unit
) {
    SettingItem(
        leftSlot = {
            Image(
                modifier = Modifier.size(40.dp),
                painter = painterResource(image),
                contentDescription = null
            )
        },
        rightSlot = {
            right()
        },
        onClick = onClick
    )
}

@Composable
internal fun SectionTitle(text: String) {
    Text(
        text = text,
        fontSize = 18.sp,
        color = MaterialTheme.colorScheme.onSecondary
    )
}

@Composable
internal fun SwitchRow(
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    SettingItem(
        leftSlot = {
            Text(
                text = title,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onSecondary
            )
        },
        rightSlot = {
            Switch(
                checked = checked,
                onCheckedChange = onCheckedChange,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = Color.Red,
                    uncheckedThumbColor = MaterialTheme.colorScheme.outline, // тёмный серый
                    uncheckedTrackColor = MaterialTheme.colorScheme.outlineVariant, // светлый серый
                    uncheckedBorderColor = Color.Transparent
                )
            )
        },
        onClick = { }
    )
}
