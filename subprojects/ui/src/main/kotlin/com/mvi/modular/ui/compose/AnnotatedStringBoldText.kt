package com.mvi.modular.ui.compose

import androidx.annotation.FloatRange
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.mvi.modular.ui.theme.MviModularTheme


@Composable
fun AnnotatedStringBoldText(
    modifier: Modifier = Modifier,
    text: String,
    placeholder: String,
    textStyle: TextStyle = TextStyle.Default,
    @FloatRange(from = 0.0, to = 1.0) scale: Float = 0.0f,
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
                }
            ),
            start = start,
            end = start + placeholder.length
        )
    )

    Text(
        modifier = modifier,
        text = AnnotatedString(text = text, spanStyles = spanStyles),
        style = textStyle
    )
}


@Preview(showBackground = true, widthDp = 320, heightDp = 200)
@Composable
private fun AnnotatedStringBoldTextPreview() {
    MviModularTheme {
        AnnotatedStringBoldText(
            modifier = Modifier.fillMaxWidth(),
            text = "This is a bold text.",
            placeholder = "bold",
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