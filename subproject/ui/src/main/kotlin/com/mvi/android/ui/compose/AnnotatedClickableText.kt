package com.mvi.android.ui.compose

import androidx.annotation.FloatRange
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.ammotel.android.ui.theme.AmmotelTheme


@Composable
fun AnnotatedClickableText(
    modifier: Modifier,
    text: String,
    placeholder: String,
    textStyle: TextStyle = TextStyle.Default,
    annotatedColor: Color = Color.Black,
    @FloatRange(from = 0.0, to = 1.0) scale: Float = 0.0f,
    onClick: () -> Unit = {},
) {
    val start = text.indexOf(placeholder)
    val spanStyles = listOf(
        AnnotatedString.Range(
            SpanStyle(
                fontWeight = FontWeight.Bold,
                fontSize = if (scale > 0) {
                    textStyle.fontSize.times(scale + 1f)
                } else {
                    textStyle.fontSize
                },
                color = annotatedColor,
                textDecoration = TextDecoration.Underline
            ),
            start = start,
            end = start + placeholder.length
        )
    )

    ClickableText(
        modifier = modifier,
        text = AnnotatedString(text = text, spanStyles = spanStyles),
        style = textStyle,
    ) {
        onClick()
    }
}


@Preview(showBackground = true, widthDp = 320, heightDp = 200)
@Composable
private fun AnnotatedClickableTextPreview() {
    AmmotelTheme {
        AnnotatedClickableText(
            modifier = Modifier.fillMaxWidth(),
            text = "This is a clickable text.",
            placeholder = "clickable",
            textStyle = TextStyle.Default.copy(
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                textAlign = TextAlign.Center,
            ),
            scale = 0.5f
        )
    }
}