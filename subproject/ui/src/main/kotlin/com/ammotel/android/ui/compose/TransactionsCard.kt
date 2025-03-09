package com.ammotel.android.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ammotel.android.strings.core.strings
import com.ammotel.android.ui.theme.AmmotelTheme
import com.ammotel.android.ui.theme.Black100
import com.ammotel.android.ui.theme.DefaultRoundCornerShape
import com.ammotel.android.ui.theme.LocalColors
import com.ammotel.android.ui.theme.LocalPadding
import com.ammotel.android.ui.theme.LocalTypography
import com.ammotel.android.ui.theme.RoundCornerSizeSmall
import com.ammotel.android.ui.theme.padding
import com.ammotel.android.utils.core.UtilsConstants.MEDIUM_DATE_FORMAT
import com.ammotel.android.utils.date.convert


enum class TransactionCardStatus {
    UNKNOWN,
    FAILED,
    SUCCESSFUL;
}


@Composable
fun TransactionsCard(
    modifier: Modifier,
    transactionCardStatus: TransactionCardStatus,
    transactionTitle: String,
    orderNumber: String,
    date: Long,
    price: String,
    onInquiryClicked: () -> Unit,
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = LocalColors.current.backgroundCardTertiary,
        ),
        shape = MaterialTheme.shapes.small,
    ) {
        Column(
            modifier = Modifier
                .padding(
                    horizontal = MaterialTheme.padding.large,
                    vertical = MaterialTheme.padding.small
                )
        ) {
            TransactionsCardHeader(
                key = transactionTitle,
                keyTextStyle = LocalTypography.current.xxExtraLarge.headline,
                transactionCardStatus = transactionCardStatus
            )

            HorizontalDivider(
                modifier = Modifier.padding(top = MaterialTheme.padding.medium)
            )

            /* Order Number (The same as TransactionNumber) */
            val rowModifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .padding(top = (MaterialTheme.padding.medium))
            val rowKeyStyle = LocalTypography.current.small.body
            val rowValueStyle = LocalTypography.current.extraSmall.body

            RowKeyValueText(
                modifier = rowModifier,
                key = stringResource(id = strings.transaction_order_number_title),
                value = orderNumber,
                keyTextStyle = rowKeyStyle,
                valueTextStyle = rowValueStyle
            )

            /*if (transactionCardStatus == TransactionCardStatus.SUCCESSFUL && details != null) {
                RowKeyValueText(
                    modifier = rowModifier,
                    key = stringResource(id = strings.general_details),
                    value = details,
                    keyTextStyle = rowKeyStyle,
                    valueTextStyle = rowValueStyle
                )
            }

            if (transactionCardStatus == TransactionCardStatus.SUCCESSFUL && coverage != null && countryCode != null) {
                RowKeyValueText(
                    modifier = rowModifier,
                    key = stringResource(id = strings.general_coverage),
                    value = "$coverage  ${countryCode.toFlagEmoji()}",
                    keyTextStyle = rowKeyStyle,
                    valueTextStyle = rowValueStyle
                )
            }*/

            val formattedDate = remember {
                date.convert(MEDIUM_DATE_FORMAT)
            }

            /* Date */
            RowKeyValueText(
                modifier = rowModifier,
                key = stringResource(id = strings.transaction_date_title),
                value = formattedDate,
                keyTextStyle = rowKeyStyle,
                valueTextStyle = rowValueStyle
            )

            /* Price */
            RowKeyValueText(
                modifier = rowModifier,
                key = stringResource(id = strings.transaction_price_title),
                value = price,
                keyTextStyle = rowKeyStyle,
                valueTextStyle = LocalTypography.current.medium.headline
            )

            if (transactionCardStatus == TransactionCardStatus.UNKNOWN) {
                Button(
                    modifier = rowModifier,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = LocalColors.current.secondaryColor,
                        contentColor = LocalColors.current.whiteLabelColorPrimary,
                    ),
                    shape = DefaultRoundCornerShape,
                    onClick = onInquiryClicked
                ) {
                    Text(
                        text = stringResource(id = strings.transaction_inquiry_title),
                        style = LocalTypography.current.large.body,
                        color = LocalColors.current.whiteLabelColorPrimary,
                    )
                }
            }
        }
    }
}

@Composable
fun TransactionsCardHeader(
    modifier: Modifier = Modifier,
    key: String,
    keyTextStyle: TextStyle? = null,
    transactionCardStatus: TransactionCardStatus = TransactionCardStatus.UNKNOWN
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Text(
            modifier = Modifier.weight(1f),
            text = key,
            style = keyTextStyle ?: LocalTextStyle.current,
        )

        val (statusTitle: String, color: Color) = getStatusUiState(transactionCardStatus = transactionCardStatus)

        Text(
            modifier = Modifier
                .background(shape = RoundCornerSizeSmall, color = color)
                .padding(
                    horizontal = MaterialTheme.padding.small,
                    vertical = MaterialTheme.padding.extraSmall
                ),

            text = statusTitle,
            color = Color.White
        )
    }
}

/**
 * Calculates status color and text for the status label based on transactionStatus
 *
 * @param transactionCardStatus an enum containing FAILED, SUCCESSFUL, and UNKNOWN statuses
 * @return [StatusUiState]
 */
@Composable
private fun getStatusUiState(transactionCardStatus: TransactionCardStatus): StatusUiState {
    var statusTitle: String = stringResource(id = strings.general_unknown)
    var color: Color = LocalColors.current.primarySecondColor
    when (transactionCardStatus) {
        TransactionCardStatus.UNKNOWN -> {
            statusTitle = stringResource(id = strings.general_unknown)
            color = LocalColors.current.primarySecondColor
        }

        TransactionCardStatus.FAILED -> {
            statusTitle = stringResource(id = strings.general_failed)
            color = LocalColors.current.errorColor
        }

        TransactionCardStatus.SUCCESSFUL -> {
            statusTitle = stringResource(id = strings.general_successful)
            color = LocalColors.current.successColor
        }
    }
    return StatusUiState(statusTitle, color)
}

private data class StatusUiState(
    val text: String,
    val color: Color
)

@Preview
@Composable
fun TransactionsCardPreview() {
    AmmotelTheme {
        TransactionsCard(
            modifier = Modifier,
            transactionCardStatus = TransactionCardStatus.UNKNOWN,
            transactionTitle = "eSIM",
            orderNumber = "566577434",
            date = System.currentTimeMillis(),
            price = "4$",
            onInquiryClicked = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TransactionsCardHeaderPreview() {
    AmmotelTheme {
        TransactionsCardHeader(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .padding(16.dp),

            key = "eSIM",
            keyTextStyle = LocalTypography.current.xxExtraLarge.headline,
            TransactionCardStatus.UNKNOWN
        )
    }
}


@Composable
fun LoadingTransactionCard(modifier: Modifier) {
    Box(
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .shimmerEffect(
                colors = ShimmerEffectDefaults.colors(
                    start = LocalColors.current.backgroundCardTertiary,
                    end = LocalColors.current.backgroundCardTertiary,
                )
            ),
    ) {
        Column(
            modifier = Modifier
                .padding(
                    horizontal = MaterialTheme.padding.large,
                    vertical = MaterialTheme.padding.small
                )
        ) {
            LoadingTransactionsCardHeader(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            )

            HorizontalDivider(
                modifier = Modifier.padding(top = MaterialTheme.padding.medium)
            )

            /* Order Number (The same as TransactionNumber) */
            val rowModifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
                .padding(top = (MaterialTheme.padding.medium))
                .background(Black100)

            repeat(5) {
                Box(modifier = rowModifier)
            }
        }
    }
}

@Preview
@Composable
fun LoadingTransactionCardPreview() {
    AmmotelTheme {
        LoadingTransactionCard(modifier = Modifier.fillMaxWidth())
    }
}

@Composable
fun LoadingTransactionsCardHeader(modifier: Modifier) {
    Box(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp, 20.dp)
                    .background(Black100)
                    .padding(LocalPadding.current.large)
            )

            Spacer(modifier = Modifier.weight(0.1f))

            Box(
                modifier = Modifier
                    .size(100.dp, 30.dp)
                    .background(
                        shape = RoundCornerSizeSmall,
                        color = LocalColors.current.successColor
                    )
                    .padding(horizontal = LocalPadding.current.large)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingTransactionsCardHeaderPreview() {
    AmmotelTheme {
        LoadingTransactionsCardHeader(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        )
    }
}