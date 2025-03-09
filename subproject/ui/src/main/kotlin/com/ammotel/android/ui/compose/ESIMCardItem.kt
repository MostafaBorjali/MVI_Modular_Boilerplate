package com.ammotel.android.ui.compose

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ammotel.android.strings.core.strings
import com.ammotel.android.ui.core.drawables
import com.ammotel.android.ui.theme.AmmotelTheme
import com.ammotel.android.ui.theme.LocalColors
import com.ammotel.android.ui.theme.LocalDimen
import com.ammotel.android.ui.theme.LocalPadding
import com.ammotel.android.ui.theme.LocalTypography
import com.ammotel.android.ui.theme.padding


@Composable
fun ESIMCardItem(
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    enableSelection: Boolean = true,
    name: String,
    code: String,
    iccId: String,
    isExpired: Boolean,
    @DrawableRes icon: Int = drawables.ic_esim,
    itemName: String = "eSIM",
    onCardClick: () -> Unit = {},
    onViewDetailsClick: () -> Unit = {},
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = LocalColors.current.backgroundCardSecondary,
        ),
        onClick = {
            onCardClick()
        },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
    ) {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            ESIMSmallCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(LocalDimen.current.esimCardHeight.div(1.8f))
                    .padding(MaterialTheme.padding.small),
                name = name,
                code = code,
                iccId = iccId,
                isExpired = isExpired,
                icon = icon
            )

            HorizontalDivider(
                modifier = Modifier
                    .padding(top = MaterialTheme.padding.small)
                    .fillMaxWidth(),
                color = LocalColors.current.lineColor,
            )

            CardBottomContent(
                modifier = Modifier.fillMaxWidth(),
                enableSelection = enableSelection,
                isCardSelected = isSelected,
                itemName = itemName,
                expired = isExpired,
                onViewDetailsClick = onViewDetailsClick
            )
        }
    }
}

@Composable
fun LoadingESIMCardItem(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .clip(MaterialTheme.shapes.large)
            .shimmerEffect(),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(LocalDimen.current.esimCardHeight.div(1.8f))
                    .padding(MaterialTheme.padding.small)
                    .clip(MaterialTheme.shapes.medium)
                    .background(LocalColors.current.backgroundCardPrimary.copy(alpha = 0.8f))
            )

            Spacer(modifier = Modifier.height(LocalPadding.current.small))

            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                color = LocalColors.current.lineColor,
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = LocalPadding.current.small)
                    .padding(start = LocalPadding.current.medium)
                    .padding(end = LocalPadding.current.small),
                verticalAlignment = Alignment.CenterVertically,
            ) {

                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape)
                        .background(LocalColors.current.lineColor)
                )

                Spacer(modifier = Modifier.width(LocalPadding.current.medium))

                Box(
                    modifier = Modifier
                        .size(100.dp, 20.dp)
                        .background(LocalColors.current.lineColor)
                )

                Spacer(modifier = Modifier.weight(1f))

                Box(
                    modifier = Modifier
                        .size(100.dp, 20.dp)
                        .background(LocalColors.current.lineColor)
                )
            }
        }
    }
}

@Suppress("UNUSED_PARAMETER")
@Composable
private fun CardBottomContent(
    modifier: Modifier,
    enableSelection: Boolean,
    isCardSelected: Boolean,
    itemName: String,
    expired: Boolean,
    onViewDetailsClick: () -> Unit,
) {
//    var menuExpanded: Boolean by remember { mutableStateOf(false) }
//    val dropDownDividerModifier = Modifier.padding(MaterialTheme.padding.extraSmall)
//    val dropDownTextStyle = LocalTypography.current.extraSmall.body

    Row(
        modifier = modifier.padding(MaterialTheme.padding.small),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (enableSelection) {
            Box(
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.padding.small)
                    .size(30.dp)
                    .border(
                        width = 4.dp,
                        color = LocalColors.current.borderIconDeActiveColor,
                        shape = CircleShape,
                    )
                    .clip(CircleShape)
                    .background(
                        if (isCardSelected) {
                            LocalColors.current.borderIconActiveColor
                        } else {
                            Color.White
                        }
                    )
                    .align(alignment = Alignment.CenterVertically)
                    .animateContentSize(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioHighBouncy,
                            stiffness = Spring.StiffnessLow
                        )
                    )
            )
        }

        Text(
            modifier = Modifier
                .weight(1f)
                .align(alignment = Alignment.CenterVertically),
            text = itemName,
            style = LocalTypography.current.xxExtraLarge.headline,
            color = LocalColors.current.blackLabelColorPrimary,
        )

        Text(
            modifier = Modifier
                .clip(MaterialTheme.shapes.extraSmall)
                .clickable(onClick = onViewDetailsClick)
                .padding(
                    horizontal = LocalPadding.current.extraSmall,
                    vertical = LocalPadding.current.extraSmall,
                ),
            text = stringResource(id = strings.plan_esim_view_details),
            style = LocalTypography.current.extraSmall.title3,
            color = LocalColors.current.blackLabelColorPrimary,
        )

        Spacer(modifier = Modifier.width(LocalPadding.current.extraSmall))

        Icon(
            painter = painterResource(id = drawables.ic_arrow_right),
            contentDescription = null
        )

//        if (expired) {
//
//        } else {
//            Button(
//                onClick = { menuExpanded = !menuExpanded }, colors = ButtonColors(
//                    containerColor = Color.Transparent,
//                    contentColor = LocalColors.current.borderIconActiveColor,
//                    disabledContentColor = LocalColors.current.labelColorSecondary,
//                    disabledContainerColor = LocalColors.current.backgroundBezeledButtonPrimaryColor
//                ), modifier = Modifier.align(alignment = Alignment.CenterVertically)
//            ) {
//                Image(
//                    painter = painterResource(id = drawables.ic_three_dot),
//                    contentDescription = null
//                )
//
//                DropdownMenu(
//                    expanded = menuExpanded,
//                    onDismissRequest = { menuExpanded = false },
//                    modifier = Modifier
//                        .width(LocalDimen.current.dropdownMenuWidth)
//                        .background(
//                            color = LocalColors.current.surfaceZ0Color,
//                            shape = DefaultRoundCornerShape
//                        )
//                ) {
//
//                    DropdownMenuItem(
//                        text = {
//                            Text(
//                                text = stringResource(id = strings.plan_esim_view_details),
//                                style = dropDownTextStyle
//                            )
//                        },
//                        onClick = { /* Handle View Details! */ },
//                    )
//
//                    HorizontalDivider(dropDownDividerModifier)
//
//                    DropdownMenuItem(text = {
//                        Text(
//                            text = stringResource(id = strings.my_esim_menu_item_top_up),
//                            style = dropDownTextStyle
//                        )
//                    }, onClick = { /* Handle Top up! */ })
//
//                    HorizontalDivider(dropDownDividerModifier)
//
//                    DropdownMenuItem(text = {
//                        Text(
//                            text = stringResource(id = strings.my_esim_menu_item_share),
//                            style = dropDownTextStyle
//                        )
//                    }, onClick = { /* Handle share */ })
//
//                    HorizontalDivider(dropDownDividerModifier)
//
//                    DropdownMenuItem(text = {
//                        Text(
//                            text = stringResource(id = strings.my_esim_menu_item_rename),
//                            style = dropDownTextStyle
//                        )
//                    }, onClick = { /* Handle rename */ })
//                }
//            }
//        }
    }
}

@Preview(showBackground = false)
@Composable
fun ESIMCardItemPreview() {
    AmmotelTheme {
        ESIMCardItem(
            modifier = Modifier,
            name = "United Arab Emirates",
            code = "TR",
            iccId = "2364534765238976",
            isExpired = false,
            icon = drawables.ic_esim,
            itemName = "eSIM"
        )
    }
}

@Preview(showBackground = false)
@Composable
fun LoadingESIMCardItemPreview() {
    AmmotelTheme {
        LoadingESIMCardItem(Modifier.fillMaxWidth())
    }
}