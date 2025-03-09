package com.ammotel.android.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ammotel.android.strings.core.strings
import com.ammotel.android.ui.theme.AmmotelTheme
import com.ammotel.android.ui.theme.LocalColors
import com.ammotel.android.ui.theme.LocalDimen
import com.ammotel.android.ui.theme.LocalPadding
import com.ammotel.android.ui.theme.LocalTypography
import com.ammotel.android.utils.core.UtilsConstants.MEDIUM_DATE_FORMAT
import com.ammotel.android.utils.date.convert
import com.ammotel.android.utils.string.mb2String


@Composable
fun ESIMPackageDetailCard(
    modifier: Modifier,
    volume: Long,
    maxVolume: Long,
    duration: Long,
    startDate: Long,
    endDate: Long,
    expired: Boolean,
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = LocalColors.current.backgroundCardTertiary,
        ),
        shape = MaterialTheme.shapes.medium,
    ) {
        Spacer(modifier = Modifier.height(LocalPadding.current.small))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = LocalPadding.current.medium),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(IntrinsicSize.Max)
                    .padding(top = LocalPadding.current.extraSmall),
                verticalArrangement = Arrangement.Center,

                ) {
                Text(
                    text = stringResource(
                        id = strings.my_esim_details_data,
                        mb2String(maxVolume, spacing = false)
                    ),
                    style = LocalTypography.current.large.title3,
                    color = LocalColors.current.blackLabelColorPrimary,
                )

                Spacer(modifier = Modifier.height(LocalPadding.current.small))

                Text(
                    text = stringResource(id = strings.my_esim_details_duration, duration),
                    style = LocalTypography.current.large.title3,
                    color = LocalColors.current.blackLabelColorPrimary,
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = LocalPadding.current.small),
                contentAlignment = Alignment.Center,
            ) {
                GaugeCard(
                    modifier = Modifier.size(LocalDimen.current.miniGaugeCardSize),
                    endTime = endDate,
                    endTimeStyle = LocalTypography.current.extraSmall.subhead,
                    endTimePadding = LocalPadding.current.large,
                    volume = volume,
                    volumeStyle = LocalTypography.current.medium.headline,
                    volumePadding = LocalPadding.current.medium,
                    maxVolume = maxVolume,
                    maxVolumeText = maxVolume,
                    enable = !expired,
                    stroke = 12.dp,
                    showMinAndMax = false,
                )
            }
        }

        HorizontalDivider(
            modifier = Modifier.padding(horizontal = LocalPadding.current.medium)
        )


        Spacer(modifier = Modifier.height(LocalPadding.current.medium))

        RowKeyValueText(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = LocalPadding.current.medium),
            key = stringResource(id = strings.my_esim_details_start_date),
            value = startDate.convert(MEDIUM_DATE_FORMAT),
            keyTextStyle = LocalTypography.current.small.body,
            valueTextStyle = LocalTypography.current.small.body,
        )

        Spacer(modifier = Modifier.height(LocalPadding.current.medium))

        RowKeyValueText(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = LocalPadding.current.medium),
            key = stringResource(id = strings.my_esim_details_end_date),
            value = endDate.convert(MEDIUM_DATE_FORMAT),
            keyTextStyle = LocalTypography.current.small.body,
            valueTextStyle = LocalTypography.current.small.body,
        )

        Spacer(modifier = Modifier.height(LocalPadding.current.medium))
    }
}


@Preview(showBackground = false)
@Composable
fun ESIMPackageDetailCardPreview() {
    AmmotelTheme {
        ESIMPackageDetailCard(
            modifier = Modifier.fillMaxWidth(),
            volume = 3689,
            maxVolume = 5120,
            duration = 7,
            startDate = System.currentTimeMillis() / 1000,
            endDate = (System.currentTimeMillis() + (24 * 60 * 60 * 1000)) / 1000,
            expired = false,
        )
    }
}