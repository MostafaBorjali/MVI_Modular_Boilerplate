@file:Suppress("unused")

package com.mvi.modular.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

/*
    Hex Opacity Values
    100% — FF
    95% — F2
    90% — E6
    85% — D9
    80% — CC
    75% — BF
    70% — B3
    65% — A6
    60% — 99
    55% — 8C
    50% — 80
    45% — 73
    40% — 66
    35% — 59
    30% — 4D
    25% — 40
    20% — 33
    15% — 26
    10% — 1A
    5% — 0D
    0% — 00
 */

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)


val PrimaryGradiantStart = Color(0xFFff1744)
val PrimaryGradiantEnd = Color(0xff880e4f)


val Orange50 = Color(0xFFFDEEE8)
val Orange100 = Color(0xFFFACAB7)
val Orange200 = Color(0xFFF8B094)
val Orange300 = Color(0xFFF48C63)
val Orange400 = Color(0xFFF27545)
val Orange500 = Color(0xFFEF5316)
val Orange500_15 = Color(0x26EF5316)
val Orange600 = Color(0xFFD94C14)
val Orange700 = Color(0xFFAA3B10)
val Orange800 = Color(0xFF832E0C)
val Orange900 = Color(0xFF642309)

val AdditionalOrange50 = Color(0xFFFDF4F0)
val AdditionalOrange100 = Color(0xFFF8DDD2)
val AdditionalOrange200 = Color(0xFFF5CDBC)
val AdditionalOrange300 = Color(0xFFF0B69D)
val AdditionalOrange400 = Color(0xFFEDA88A)
val AdditionalOrange500 = Color(0xFFE9926D)
val AdditionalOrange600 = Color(0xFFD48563)
val AdditionalOrange700 = Color(0xFFA5684D)
val AdditionalOrange800 = Color(0xFF80503C)
val AdditionalOrange900 = Color(0xFF623D2E)

val Yellow50 = Color(0xFFFAF6E8)
val Yellow100 = Color(0xFFFFEFBC)
val Yellow200 = Color(0xFFFFE79C)
val Yellow300 = Color(0xFFFFDC6E)
val Yellow400 = Color(0xFFFFD552)
val Yellow500 = Color(0xFFFFCB27)
val Yellow600 = Color(0xFFE8B923)
val Yellow700 = Color(0xFFB5901C)
val Yellow800 = Color(0xFF8C7015)
val Yellow900 = Color(0xFF6B5510)

val AdditionalYellow50 = Color(0xFFF9F5ED)
val AdditionalYellow100 = Color(0xFFECDfC6)
val AdditionalYellow200 = Color(0xFFE2D0AB)
val AdditionalYellow300 = Color(0xFFD5BA84)
val AdditionalYellow400 = Color(0xFFCDAD6D)
val AdditionalYellow500 = Color(0xFFC19848)
val AdditionalYellow600 = Color(0xFFB08A42)
val AdditionalYellow700 = Color(0xFF896C33)
val AdditionalYellow800 = Color(0xFF6A5428)
val AdditionalYellow900 = Color(0xFF51401E)

val Red50 = Color(0xFFF2E7E7)
val Red100 = Color(0xFFD6B6B4)
val Red200 = Color(0xFFC39290)
val Red300 = Color(0xFFA7605D)
val Red400 = Color(0xFF96413D)
val Red500 = Color(0xFF7C120D)
val Red600 = Color(0xFF71100C)
val Red700 = Color(0xFF580D09)
val Red800 = Color(0xFF440A07)
val Red900 = Color(0xFF340805)

val Grey50 = Color(0xFFEAEAEA)
val Grey100 = Color(0xFFBEBEBE)
val Grey200 = Color(0xFF9E9E9E)
val Grey300 = Color(0xFF727272)
val Grey400 = Color(0xFF575757)
val Grey500 = Color(0xFF2D2D2D)
val Grey600 = Color(0xFF292929)
val Grey700 = Color(0xFF202020)
val Grey800 = Color(0xFF191919)
val Grey900 = Color(0xFF131313)

val Green50 = Color(0xFFEBF9EE)
val Green100 = Color(0xFFC0EECC)
val Green200 = Color(0xFFA2E5B3)
val Green300 = Color(0xFF77D990)
val Green400 = Color(0xFF5DD27A)
val Green500 = Color(0xFF34C759)
val Green600 = Color(0xFF2FB551)
val Green700 = Color(0xFF258D3F)
val Green800 = Color(0xFF1D6D31)
val Green900 = Color(0xFF165425)

val AdditionalGreen50 = Color(0xFFE7F5F2)
val AdditionalGreen100 = Color(0xFFB5DFD6)
val AdditionalGreen200 = Color(0xFF91D0C3)
val AdditionalGreen300 = Color(0xFF5EBAA7)
val AdditionalGreen400 = Color(0xFF3FAD96)
val AdditionalGreen500 = Color(0xFF0F987C)
val AdditionalGreen600 = Color(0xFF0E8A71)
val AdditionalGreen700 = Color(0xFF0B6C58)
val AdditionalGreen800 = Color(0xFF085444)
val AdditionalGreen900 = Color(0xFF064034)

val Blue50 = Color(0xFFE6F2FF)
val Blue100 = Color(0xFFB0D6FF)
val Blue200 = Color(0xFF8AC2FF)
val Blue300 = Color(0xFF54A6FF)
val Blue400 = Color(0xFF3395FF)
val Blue500 = Color(0xFF007AFF)
val Blue600 = Color(0xFF006FE8)
val Blue700 = Color(0xFF0057B5)
val Blue800 = Color(0xFF00438C)
val Blue900 = Color(0xFF00336B)

val AdditionalBlue50 = Color(0xFFF0EEFC)
val AdditionalBlue100 = Color(0xFFD1CBF6)
val AdditionalBlue200 = Color(0xFFBBB2F2)
val AdditionalBlue300 = Color(0xFF9C8FEC)
val AdditionalBlue400 = Color(0xFF8979E9)
val AdditionalBlue500 = Color(0xFF6B58E3)
val AdditionalBlue600 = Color(0xFF6150CF)
val AdditionalBlue700 = Color(0xFF4C3EA1)
val AdditionalBlue800 = Color(0xFF3B307D)
val AdditionalBlue900 = Color(0xFF2D255F)

val ErrorRed50 = Color(0xFFFCE7E7)
val ErrorRed100 = Color(0xFFF6B6B6)
val ErrorRed200 = Color(0xFFF29292)
val ErrorRed300 = Color(0xFFEC6161)
val ErrorRed400 = Color(0xFFE84242)
val ErrorRed500 = Color(0xFFE21313)
val ErrorRed600 = Color(0xFFCE1111)
val ErrorRed700 = Color(0xFFA00D0D)
val ErrorRed800 = Color(0xFF7C0A0A)
val ErrorRed900 = Color(0xFF5F0808)

val Black50 = Color(0xFFE6E6E6)
val Black100 = Color(0xFFB0B0B0)
val Black200 = Color(0xFF8A8A8A)
val Black200_24 = Color(0x408A8A8A)
val Black300 = Color(0xFF545454)
val Black400 = Color(0xFF333333)
val Black500 = Color(0xFF000000)
val Black = Color(0x7F000000)

val White500 = Color(0xFFFFFFFF)
val White500_75 = Color(0xEFFFFFFF)
val White600 = Color(0xFFFDFDFD)
val White700 = Color(0xFFF7F7F7)
val White800 = Color(0xFFEDEDED)
val White900 = Color(0xFFD7D7D7)
val White950 = Color(0xFFB7B7B7)

val AdditionalPink50 = Color(0xFFFCEEFC)
val AdditionalPink100 = Color(0xFFF6CBF5)
val AdditionalPink200 = Color(0xFFF2B2F0)
val AdditionalPink300 = Color(0xFFEC8FE9)
val AdditionalPink400 = Color(0xFFE979E5)
val AdditionalPink500 = Color(0xFFE358DE)
val AdditionalPink600 = Color(0xFFCF50CA)
val AdditionalPink700 = Color(0xFFA13E9E)
val AdditionalPink800 = Color(0xFF7D307A)
val AdditionalPink900 = Color(0xFF5F255D)

val AdditionalPurple50 = Color(0xFFF5F2F7)
val AdditionalPurple100 = Color(0xFFE1D5E7)
val AdditionalPurple200 = Color(0xFFD2C1DC)
val AdditionalPurple300 = Color(0xFFBEA5CB)
val AdditionalPurple400 = Color(0xFFB193C1)
val AdditionalPurple500 = Color(0xFF9E78B2)
val AdditionalPurple600 = Color(0xFF906DA2)
val AdditionalPurple700 = Color(0xFF70557E)
val AdditionalPurple800 = Color(0xFF574262)
val AdditionalPurple900 = Color(0xFF42324B)


val LocalColors = compositionLocalOf { MviModularColors }

@Immutable
data class Colors(
    val blackLabelColorPrimary: Color,
    val whiteLabelColorPrimary: Color,
    val labelColorSecondary: Color,
    val whiteLabelColorSecondary: Color,
    val labelColorTertiary: Color,
    val labelColorQuaternary: Color,
    val borderlessButtonPrimaryColor: Color,
    val borderlessButtonSecondaryColor: Color,
    val backgroundBezeledButtonPrimaryColor: Color,
    val backgroundBezeledButtonSecondaryColor: Color,
    val primaryFirstColor: Color,
    val primarySecondColor: Color,
    val primaryThirdColor: Color,
    val secondaryColor: Color,
    val errorColor: Color,
    val successColor: Color,
    val backgroundSearchColor: Color,
    val textFieldBorderColorDefault: Color,
    val textFieldBackgroundColor: Color,
    val textFieldBorderColorActive: Color,
    val indicatorColor: Color,
    val textFieldBorderColorError: Color,
    val borderIconActiveColor: Color,
    val borderIconSelectedColor: Color,
    val borderIconDeActiveColor: Color,
    val navbarBackgroundColor: Color,
    val bottomSheetBackgroundColor: Color,
    val backgroundCardPrimary: Color,
    val surfaceZ0Color: Color,
    val surfaceZ1Color: Color,
    val backgroundCardSecondary: Color,
    val backgroundCardTertiary: Color,
    val paginationDeActiveColor: Color,
    val paginationActiveColor: Color,
    val lineColor: Color,
    val bottomSheetIndicatorColor: Color,
    val logoTextColorWhite: Color,
    val logoTextColorBlack: Color,
    val logoSignColorWhite: Color,
    val logoSignColorBlack: Color,
    val logoSignColorPrimary: Color,
    val buttonPrimaryGradiantStart: Color,
    val buttonPrimaryGradiantEnd: Color,
    val cardAngledGradientStart: Color,
    val cardAngledGradientEnd: Color,
    val buttonGradiantTextColor: Color,
    val primaryGradiantStart: Color,
    val primaryGradiantEnd: Color,
    val buttonColor: Color,
)


internal val MviModularColors = Colors(
    blackLabelColorPrimary = Black500,
    whiteLabelColorPrimary = White500,
    labelColorSecondary = Black300,
    whiteLabelColorSecondary = Black50,
    labelColorTertiary = Black200,
    labelColorQuaternary = Black100,
    borderlessButtonPrimaryColor = Orange500,
    borderlessButtonSecondaryColor = Black300,
    backgroundBezeledButtonPrimaryColor = Orange500_15,
    backgroundBezeledButtonSecondaryColor = Black200_24,
    primaryFirstColor = Red500,
    primarySecondColor = Orange500,
    primaryThirdColor = Yellow500,
    secondaryColor = Grey500,
    errorColor = ErrorRed500,
    successColor = Green500,
    backgroundSearchColor = Black50,
    textFieldBorderColorDefault = Black100,
    textFieldBackgroundColor = White500,
    textFieldBorderColorActive = Blue500,
    indicatorColor = Blue500,
    textFieldBorderColorError = ErrorRed500,
    borderIconActiveColor = Black400,
    borderIconSelectedColor = Red500,
    borderIconDeActiveColor = Black100,
    navbarBackgroundColor = White500_75,
    bottomSheetBackgroundColor = White700,
    backgroundCardPrimary = Red500,
    surfaceZ0Color = White600,
    surfaceZ1Color = Yellow50,
    backgroundCardSecondary = Grey50,
    backgroundCardTertiary = White700,
    paginationDeActiveColor = Grey100,
    paginationActiveColor = Orange500,
    lineColor = Grey200,
    bottomSheetIndicatorColor = Black100,
    logoTextColorWhite = White500,
    logoTextColorBlack = Black500,
    logoSignColorWhite = White500,
    logoSignColorBlack = Black500,
    logoSignColorPrimary = Orange500,
    buttonPrimaryGradiantStart = PrimaryGradiantStart,
    buttonPrimaryGradiantEnd = PrimaryGradiantEnd,
    cardAngledGradientStart = Red500,
    cardAngledGradientEnd = Black,
    buttonGradiantTextColor = White500,
    primaryGradiantStart = PrimaryGradiantStart,
    primaryGradiantEnd = PrimaryGradiantEnd,
    buttonColor = Grey500
)
