package com.ammotel.android.ui.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ammotel.android.ui.theme.AmmotelTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PullToRefreshLayout(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isRefreshing: Boolean = false,
    onRefresh: () -> Unit,
    content: @Composable () -> Unit,
) {
    val pullToRefreshState = rememberPullToRefreshState(
        positionalThreshold = 100.dp,
        enabled = { enabled }
    )

    Box(
        modifier = modifier.nestedScroll(pullToRefreshState.nestedScrollConnection)
    ) {

        content()

        if (pullToRefreshState.isRefreshing) {
            LaunchedEffect(true) {
                onRefresh()
            }
        }

        LaunchedEffect(isRefreshing) {
            if (isRefreshing) {
                pullToRefreshState.startRefresh()
            } else {
                pullToRefreshState.endRefresh()
            }
        }

        PullToRefreshContainer(
            modifier = Modifier.align(Alignment.TopCenter),
            state = pullToRefreshState,
        )
    }
}


@Preview(showBackground = true, widthDp = 320, heightDp = 480)
@Composable
fun PullToRefreshLayoutPreview() {
    AmmotelTheme {
        PullToRefreshLayout(
            modifier = Modifier.fillMaxSize(),
            content = {
                Text(text = "Hello from pull to refresh layout")
            },
            isRefreshing = true,
            onRefresh = {}
        )
    }
}