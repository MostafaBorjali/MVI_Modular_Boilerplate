package com.mvi.modular.home.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mvi.modular.home.screen.home.event.HomeScreenEvent
import com.mvi.modular.home.screen.home.state.HomeScreenUiState
import com.mvi.modular.movie.domain.model.MovieDto
import com.mvi.modular.strings.core.strings
import com.mvi.modular.ui.compose.Dialog
import com.mvi.modular.ui.theme.LocalColors
import com.mvi.modular.ui.theme.LocalPadding
import com.mvi.modular.ui.theme.LocalTypography
import com.mvi.modular.ui.theme.MviModularTheme
import com.mvi.modular.ui.theme.padding
import kotlinx.coroutines.flow.StateFlow


@Composable
internal fun HomeScreen(
    state: StateFlow<HomeScreenUiState>,
    onEvent: (HomeScreenEvent) -> Unit,
) {
    val uiState by state.collectAsState()
    LaunchedEffect(key1 = true) {
        onEvent(HomeScreenEvent.LoadTopMovies(1))
    }
    HomeContent(Modifier, uiState)
}

@Composable
private fun HomeContent(
    modifier: Modifier,
    uiState: HomeScreenUiState
) {
    var errorDialog by remember {
        mutableStateOf(false)
    }
    if (errorDialog) {
        val errorInfo = uiState.getTopMoviesError?.consume()
        if (errorInfo != null) {
            Dialog(
                title = stringResource(id = strings.general_error),
                cancelable = errorInfo.cancelable,
                retryable = errorInfo.retryable,
                message = errorInfo.message,
                onDismissRequest = {
                    errorDialog = false
                },
                onPositiveClicked = {

                    errorDialog = false
                },
                onNegativeClicked = {
                    errorDialog = false
                }
            )
        } else {
            errorDialog = false
        }
    } else {
        errorDialog = uiState.getTopMoviesError?.handled == false
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(LocalColors.current.surfaceZ1Color)
    ) {
        if (uiState.topMovies == null) {
            Text(
                modifier = Modifier
                    .padding(
                        top = MaterialTheme.padding.small,
                        start = MaterialTheme.padding.extraLarge,
                        end = MaterialTheme.padding.extraLarge
                    )
                    .align(Alignment.Center),
                text = stringResource(id = strings.home_title),
                textAlign = TextAlign.Start,
                style = LocalTypography.current.large.body,
                color = LocalColors.current.blackLabelColorPrimary,
            )
        } else {
            LazyColumn(Modifier.padding(top = LocalPadding.current.extraLarge, bottom = 116.dp)) {
                items(uiState.topMovies) {
                    ItemOFList(it)

                }
            }

        }


    }
}

@Composable
fun ItemOFList(movie: MovieDto) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 8.dp, start = 24.dp, end = 24.dp)
                .background(Gray, shape = RoundedCornerShape(24.dp)),
            contentAlignment = Alignment.Center
        ) {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://media.themoviedb.org/t/p/w220_and_h330_face${movie.posterPath}")
                    .crossfade(true)
                    .build(),
                // placeholder = painterResource(drawables.ic_movies_logo_white),
                contentDescription = stringResource(strings.app_name),
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(24.dp)),
            )
            Text(
                modifier = Modifier
                    .padding(
                        top = MaterialTheme.padding.small,
                        start = MaterialTheme.padding.extraLarge,
                        end = MaterialTheme.padding.extraLarge,

                        ),
                text = movie.title,
                textAlign = TextAlign.Start,
                style = LocalTypography.current.large.titleLarge,
                color = LocalColors.current.whiteLabelColorPrimary,
            )

        }
        Text(
            modifier = Modifier
                .padding(
                    top = MaterialTheme.padding.extraSmall,
                    start = MaterialTheme.padding.extraLarge,
                    end = MaterialTheme.padding.extraLarge,
                    bottom = MaterialTheme.padding.medium
                ),
            text = movie.overview,
            textAlign = TextAlign.Start,
            style = LocalTypography.current.large.body,
            color = LocalColors.current.blackLabelColorPrimary,
        )
    }


}

@Preview(showBackground = true)
@Composable
fun HomeContentPreview() {
    MviModularTheme {
        HomeContent(Modifier, HomeScreenUiState())
    }
}

@Preview(showBackground = true)
@Composable
fun ItemPreview() {
    MviModularTheme {
        ItemOFList(
            MovieDto(
                title = "Title",
                overview = "overView overView overView overView overView overView",
                posterPath = "adf"
            )
        )
    }
}