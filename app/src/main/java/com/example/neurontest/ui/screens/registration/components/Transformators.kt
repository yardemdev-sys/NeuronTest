package com.example.neurontest.ui.screens.registration.components

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class CardNumberTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val raw = text.text
        val out = buildString {
            raw.forEachIndexed { i, c ->
                append(c)
                if ((i + 1) % 4 == 0 && i != raw.lastIndex) append(' ')
            }
        }

        val offsetMapping = object : OffsetMapping {
            private fun spacesBeforeOriginal(offset: Int): Int =
                if (offset <= 0) 0 else (offset - 1) / 4

            private fun spacesBeforeTransformed(offset: Int): Int =
                if (offset <= 0) 0 else offset / 5

            override fun originalToTransformed(offset: Int): Int =
                offset + spacesBeforeOriginal(offset)

            override fun transformedToOriginal(offset: Int): Int =
                offset - spacesBeforeTransformed(offset)
        }

        return TransformedText(AnnotatedString(out), offsetMapping)
    }
}
