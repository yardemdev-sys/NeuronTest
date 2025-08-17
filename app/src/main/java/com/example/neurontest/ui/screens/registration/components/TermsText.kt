package com.example.neurontest.ui.screens.registration.components

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import com.example.neurontest.R

@Composable
fun TermsText(onTermsClick: () -> Unit) {
    val tag = "terms"
    val annotated = buildAnnotatedString {
        append(stringResource(R.string.terms))
        append(" ")
        pushStringAnnotation(tag = tag, annotation = "terms")
        withStyle(
            SpanStyle(
                color = MaterialTheme.colorScheme.primary,
                textDecoration = TextDecoration.Underline
            )
        ) { append(stringResource(R.string.terms_link)) }
        pop()
    }

    var layout by remember { mutableStateOf<TextLayoutResult?>(null) }

    Text(
        text = annotated,
        style = MaterialTheme.typography.bodyMedium.copy(
            color = MaterialTheme.colorScheme.onSurface
        ),
        onTextLayout = { layout = it },
        modifier = Modifier.pointerInput(annotated) {
            detectTapGestures { pos ->
                val offset = layout?.getOffsetForPosition(pos) ?: return@detectTapGestures
                val hit = annotated.getStringAnnotations(tag, offset, offset).firstOrNull()
                if (hit != null) onTermsClick()
            }
        },
        textAlign = TextAlign.Center
    )
}
