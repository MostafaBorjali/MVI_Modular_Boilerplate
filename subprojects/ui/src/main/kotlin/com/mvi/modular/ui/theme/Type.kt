package com.mvi.modular.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Immutable
data class MviModularBoilerplateTypography(
    val extraSmall: MviModularTextStyle,
    val small: MviModularTextStyle,
    val medium: MviModularTextStyle,
    val large: MviModularTextStyle,
    val extraLarge: MviModularTextStyle,
    val xExtraLarge: MviModularTextStyle,
    val xxExtraLarge: MviModularTextStyle,
    val ax1: MviModularTextStyle,
    val ax2: MviModularTextStyle,
    val ax3: MviModularTextStyle,
    val ax4: MviModularTextStyle,
    val ax5: MviModularTextStyle,
)

@Immutable
data class MviModularTextStyle(
    val titleLarge: TextStyle,
    val title1: TextStyle,
    val title2: TextStyle,
    val title3: TextStyle,
    val headline: TextStyle,
    val body: TextStyle,
    val callOut: TextStyle,
    val subhead: TextStyle,
    val footnote: TextStyle,
    val caption1: TextStyle,
    val caption2: TextStyle,
)


val LocalTypography = compositionLocalOf { DefaultTypography }


internal val DefaultTypography = MviModularBoilerplateTypography(
    extraSmall = MviModularTextStyle(
        titleLarge = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 31.sp,
            lineHeight = 38.sp,
        ),
        title1 = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 25.sp,
            lineHeight = 31.sp,
        ),
        title2 = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 19.sp,
            lineHeight = 23.sp,
        ),
        title3 = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 17.sp,
            lineHeight = 22.sp,
        ),
        headline = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            lineHeight = 19.sp,
        ),
        body = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 19.sp,
        ),
        callOut = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 13.sp,
            lineHeight = 18.sp,
        ),
        subhead = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 16.sp,
        ),
        footnote = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 16.sp,
        ),
        caption1 = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 11.sp,
            lineHeight = 13.sp,
        ),
        caption2 = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 11.sp,
            lineHeight = 13.sp,
        ),
    ),
    small = MviModularTextStyle(
        titleLarge = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 32.sp,
            lineHeight = 39.sp,
        ),
        title1 = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 26.sp,
            lineHeight = 32.sp,
        ),
        title2 = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp,
            lineHeight = 24.sp,
        ),
        title3 = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
            lineHeight = 23.sp,
        ),
        headline = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            lineHeight = 20.sp,
        ),
        body = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 15.sp,
            lineHeight = 20.sp,
        ),
        callOut = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 19.sp,
        ),
        subhead = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 13.sp,
            lineHeight = 18.sp,
        ),
        footnote = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 16.sp,
        ),
        caption1 = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 11.sp,
            lineHeight = 13.sp,
        ),
        caption2 = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 11.sp,
            lineHeight = 13.sp,
        ),
    ),
    medium = MviModularTextStyle(
        titleLarge = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 33.sp,
            lineHeight = 40.sp,
        ),
        title1 = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 27.sp,
            lineHeight = 33.sp,
        ),
        title2 = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 21.sp,
            lineHeight = 28.sp,
        ),
        title3 = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 19.sp,
            lineHeight = 24.sp,
        ),
        headline = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            lineHeight = 21.sp,
        ),
        body = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 21.sp,
        ),
        callOut = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 15.sp,
            lineHeight = 20.sp,
        ),
        subhead = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 19.sp,
        ),
        footnote = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 16.sp,
        ),
        caption1 = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 11.sp,
            lineHeight = 13.sp,
        ),
        caption2 = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 11.sp,
            lineHeight = 13.sp,
        ),
    ),
    large = MviModularTextStyle(
        titleLarge = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 34.sp,
            lineHeight = 41.sp,
        ),
        title1 = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 28.sp,
            lineHeight = 34.sp,
        ),
        title2 = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 22.sp,
            lineHeight = 28.sp,
        ),
        title3 = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp,
            lineHeight = 25.sp,
        ),
        headline = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp,
            lineHeight = 22.sp,
        ),
        body = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 17.sp,
            lineHeight = 22.sp,
        ),
        callOut = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 21.sp,
        ),
        subhead = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 15.sp,
            lineHeight = 20.sp,
        ),
        footnote = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 13.sp,
            lineHeight = 18.sp,
        ),
        caption1 = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 16.sp,
        ),
        caption2 = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 11.sp,
            lineHeight = 13.sp,
        ),
    ),
    extraLarge = MviModularTextStyle(
        titleLarge = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 36.sp,
            lineHeight = 43.sp,
        ),
        title1 = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 30.sp,
            lineHeight = 36.sp,
        ),
        title2 = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 24.sp,
            lineHeight = 30.sp,
        ),
        title3 = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 22.sp,
            lineHeight = 28.sp,
        ),
        headline = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 19.sp,
            lineHeight = 24.sp,
        ),
        body = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 19.sp,
            lineHeight = 24.sp,
        ),
        callOut = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
            lineHeight = 23.sp,
        ),
        subhead = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 17.sp,
            lineHeight = 22.sp,
        ),
        footnote = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 15.sp,
            lineHeight = 20.sp,
        ),
        caption1 = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 19.sp,
        ),
        caption2 = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 13.sp,
            lineHeight = 18.sp,
        ),
    ),
    xExtraLarge = MviModularTextStyle(
        titleLarge = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 38.sp,
            lineHeight = 46.sp,
        ),
        title1 = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 32.sp,
            lineHeight = 39.sp,
        ),
        title2 = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 26.sp,
            lineHeight = 32.sp,
        ),
        title3 = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 24.sp,
            lineHeight = 30.sp,
        ),
        headline = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 21.sp,
            lineHeight = 26.sp,
        ),
        body = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 21.sp,
            lineHeight = 26.sp,
        ),
        callOut = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp,
            lineHeight = 24.sp,
        ),
        subhead = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 19.sp,
            lineHeight = 24.sp,
        ),
        footnote = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 17.sp,
            lineHeight = 22.sp,
        ),
        caption1 = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 21.sp,
        ),
        caption2 = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 15.sp,
            lineHeight = 20.sp,
        ),
    ),
    xxExtraLarge = MviModularTextStyle(
        titleLarge = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 40.sp,
            lineHeight = 48.sp,
        ),
        title1 = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 34.sp,
            lineHeight = 41.sp,
        ),
        title2 = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 28.sp,
            lineHeight = 34.sp,
        ),
        title3 = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 26.sp,
            lineHeight = 32.sp,
        ),
        headline = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 23.sp,
            lineHeight = 29.sp,
        ),
        body = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 23.sp,
            lineHeight = 29.sp,
        ),
        callOut = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 22.sp,
            lineHeight = 28.sp,
        ),
        subhead = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 21.sp,
            lineHeight = 28.sp,
        ),
        footnote = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 19.sp,
            lineHeight = 24.sp,
        ),
        caption1 = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
            lineHeight = 23.sp,
        ),
        caption2 = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 17.sp,
            lineHeight = 22.sp,
        ),
    ),
    ax1 = MviModularTextStyle(
        titleLarge = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 44.sp,
            lineHeight = 52.sp,
        ),
        title1 = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 38.sp,
            lineHeight = 46.sp,
        ),
        title2 = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 34.sp,
            lineHeight = 41.sp,
        ),
        title3 = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 31.sp,
            lineHeight = 38.sp,
        ),
        headline = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            lineHeight = 34.sp,
        ),
        body = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 28.sp,
            lineHeight = 34.sp,
        ),
        callOut = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 26.sp,
            lineHeight = 32.sp,
        ),
        subhead = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 25.sp,
            lineHeight = 31.sp,
        ),
        footnote = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 23.sp,
            lineHeight = 29.sp,
        ),
        caption1 = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 22.sp,
            lineHeight = 28.sp,
        ),
        caption2 = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp,
            lineHeight = 25.sp,
        ),
    ),
    ax2 = MviModularTextStyle(
        titleLarge = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 48.sp,
            lineHeight = 57.sp,
        ),
        title1 = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 43.sp,
            lineHeight = 51.sp,
        ),
        title2 = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 40.sp,
            lineHeight = 51.sp,
        ),
        title3 = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 37.sp,
            lineHeight = 44.sp,
        ),
        headline = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 33.sp,
            lineHeight = 40.sp,
        ),
        body = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 33.sp,
            lineHeight = 40.sp,
        ),
        callOut = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 32.sp,
            lineHeight = 39.sp,
        ),
        subhead = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 30.sp,
            lineHeight = 37.sp,
        ),
        footnote = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 27.sp,
            lineHeight = 33.sp,
        ),
        caption1 = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 26.sp,
            lineHeight = 32.sp,
        ),
        caption2 = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 24.sp,
            lineHeight = 30.sp,
        ),
    ),
    ax3 = MviModularTextStyle(
        titleLarge = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 52.sp,
            lineHeight = 61.sp,
        ),
        title1 = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 48.sp,
            lineHeight = 57.sp,
        ),
        title2 = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 44.sp,
            lineHeight = 52.sp,
        ),
        title3 = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 43.sp,
            lineHeight = 51.sp,
        ),
        headline = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp,
            lineHeight = 48.sp,
        ),
        body = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 40.sp,
            lineHeight = 48.sp,
        ),
        callOut = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 38.sp,
            lineHeight = 46.sp,
        ),
        subhead = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 36.sp,
            lineHeight = 43.sp,
        ),
        footnote = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 33.sp,
            lineHeight = 40.sp,
        ),
        caption1 = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 32.sp,
            lineHeight = 39.sp,
        ),
        caption2 = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 29.sp,
            lineHeight = 35.sp,
        ),
    ),
    ax4 = MviModularTextStyle(
        titleLarge = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 56.sp,
            lineHeight = 66.sp,
        ),
        title1 = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 53.sp,
            lineHeight = 62.sp,
        ),
        title2 = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 50.sp,
            lineHeight = 59.sp,
        ),
        title3 = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 43.sp,
            lineHeight = 51.sp,
        ),
        headline = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 47.sp,
            lineHeight = 56.sp,
        ),
        body = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 47.sp,
            lineHeight = 56.sp,
        ),
        callOut = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 44.sp,
            lineHeight = 52.sp,
        ),
        subhead = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 42.sp,
            lineHeight = 50.sp,
        ),
        footnote = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 38.sp,
            lineHeight = 46.sp,
        ),
        caption1 = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 37.sp,
            lineHeight = 44.sp,
        ),
        caption2 = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 34.sp,
            lineHeight = 41.sp,
        ),
    ),
    ax5 = MviModularTextStyle(
        titleLarge = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 60.sp,
            lineHeight = 70.sp,
        ),
        title1 = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 58.sp,
            lineHeight = 68.sp,
        ),
        title2 = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 56.sp,
            lineHeight = 66.sp,
        ),
        title3 = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 55.sp,
            lineHeight = 65.sp,
        ),
        headline = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 53.sp,
            lineHeight = 62.sp,
        ),
        body = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 53.sp,
            lineHeight = 62.sp,
        ),
        callOut = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 51.sp,
            lineHeight = 60.sp,
        ),
        subhead = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 49.sp,
            lineHeight = 58.sp,
        ),
        footnote = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 44.sp,
            lineHeight = 52.sp,
        ),
        caption1 = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 43.sp,
            lineHeight = 51.sp,
        ),
        caption2 = TextStyle(
            fontFamily = RobotoCondensedFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 40.sp,
            lineHeight = 48.sp,
        ),
    ),
)

/**
 *
 */
internal val SmallTypography = MviModularBoilerplateTypography(
    extraSmall = DefaultTypography.extraSmall.scaleDown(),
    small = DefaultTypography.small.scaleDown(),
    medium = DefaultTypography.medium.scaleDown(),
    large = DefaultTypography.large.scaleDown(),
    extraLarge = DefaultTypography.extraLarge.scaleDown(),
    xExtraLarge = DefaultTypography.xExtraLarge.scaleDown(),
    xxExtraLarge = DefaultTypography.xxExtraLarge.scaleDown(),
    ax1 = DefaultTypography.ax1.scaleDown(),
    ax2 = DefaultTypography.ax2.scaleDown(),
    ax3 = DefaultTypography.ax3.scaleDown(),
    ax4 = DefaultTypography.ax4.scaleDown(),
    ax5 = DefaultTypography.ax5.scaleDown(),
)

/**
 *
 */
internal val LargeTypography = MviModularBoilerplateTypography(
    extraSmall = DefaultTypography.extraSmall.scaleUp(),
    small = DefaultTypography.small.scaleUp(),
    medium = DefaultTypography.medium.scaleUp(),
    large = DefaultTypography.large.scaleUp(),
    extraLarge = DefaultTypography.extraLarge.scaleUp(),
    xExtraLarge = DefaultTypography.xExtraLarge.scaleUp(),
    xxExtraLarge = DefaultTypography.xxExtraLarge.scaleUp(),
    ax1 = DefaultTypography.ax1.scaleUp(),
    ax2 = DefaultTypography.ax2.scaleUp(),
    ax3 = DefaultTypography.ax3.scaleUp(),
    ax4 = DefaultTypography.ax4.scaleUp(),
    ax5 = DefaultTypography.ax5.scaleUp(),
)


private fun TextStyle.scaleDown(): TextStyle {
    return this.copy(
        fontSize = this.fontSize.div(1.2f),
        lineHeight = this.lineHeight.div(1.2f),
    )
}

private fun TextStyle.scaleUp(): TextStyle {
    return this.copy(
        fontSize = this.fontSize.times(1.3f),
        lineHeight = this.lineHeight.times(1.3f),
    )
}

private fun MviModularTextStyle.scaleDown(): MviModularTextStyle {
    return this.copy(
        titleLarge = this.titleLarge.scaleDown(),
        title1 = this.title1.scaleDown(),
        title2 = this.title2.scaleDown(),
        title3 = this.title3.scaleDown(),
        headline = this.headline.scaleDown(),
        body = this.body.scaleDown(),
        callOut = this.callOut.scaleDown(),
        subhead = this.subhead.scaleDown(),
        footnote = this.footnote.scaleDown(),
        caption1 = this.caption1.scaleDown(),
        caption2 = this.caption2.scaleDown(),
    )
}

private fun MviModularTextStyle.scaleUp(): MviModularTextStyle {
    return this.copy(
        titleLarge = this.titleLarge.scaleUp(),
        title1 = this.title1.scaleUp(),
        title2 = this.title2.scaleUp(),
        title3 = this.title3.scaleUp(),
        headline = this.headline.scaleUp(),
        body = this.body.scaleUp(),
        callOut = this.callOut.scaleUp(),
        subhead = this.subhead.scaleUp(),
        footnote = this.footnote.scaleUp(),
        caption1 = this.caption1.scaleUp(),
        caption2 = this.caption2.scaleUp(),
    )
}
