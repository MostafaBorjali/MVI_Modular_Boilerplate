package com.mvi.modular.intro.screen

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentResolver
import android.net.Uri
import android.util.Log
import android.view.ViewGroup
import androidx.annotation.OptIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player.Listener
import androidx.media3.common.Player.REPEAT_MODE_ONE
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.mvi.modular.intro.R
import com.mvi.modular.intro.screen.event.IntroScreenEvent
import com.mvi.modular.intro.screen.state.IntroScreenState
import com.mvi.modular.strings.core.strings
import com.mvi.modular.ui.compose.GradientButton
import com.mvi.modular.ui.compose.SelectLanguage
import com.mvi.modular.ui.core.drawables
import com.mvi.modular.ui.theme.LocalColors
import com.mvi.modular.ui.theme.LocalTypography
import com.mvi.modular.ui.theme.MviModularTheme
import com.mvi.modular.ui.theme.padding
import kotlinx.coroutines.flow.StateFlow
import java.lang.ref.WeakReference


@SuppressLint("ContextCastToActivity")
@Composable
fun IntroScreen(
    modifier: Modifier,
    uiState: StateFlow<IntroScreenState>,
    onEvent: (IntroScreenEvent) -> Unit,
    onClick: () -> Unit = {}
) {
    val introUiState by uiState.collectAsState()
    var showSheet by remember { mutableStateOf(false) }
    val activity = (LocalContext.current as? Activity)

    LaunchedEffect(key1 = true) {
        onEvent(IntroScreenEvent.GetLangConfig)
    }

    Box(modifier = modifier.background(Color.Black)) {
        VideoPlayer(modifier = Modifier.fillMaxSize())

        if (showSheet) {
            SelectLanguage(
                languages = introUiState.languages,
            ) { lang ->
                if (lang != null) {
                    if (lang != introUiState.currentLang) {
                        onEvent(IntroScreenEvent.SelectLanguage(lang))
                        activity?.recreate()
                    }
                }
                showSheet = false
            }
        }

        IntroTopBar(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(
                    top = MaterialTheme.padding.medium,
                    start = MaterialTheme.padding.large,
                    end = MaterialTheme.padding.large,
                ),
            language = introUiState.currentLang.languageCode,
        ) {
            showSheet = true
        }

        IntroContent(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .align(Alignment.BottomStart)
                .padding(
                    bottom = MaterialTheme.padding.medium,
                    start = MaterialTheme.padding.large,
                    end = MaterialTheme.padding.large
                )
                .navigationBarsPadding(),
            onClick = {
                onEvent(IntroScreenEvent.Accept)
                onClick()
            }
        )
    }
}

@OptIn(UnstableApi::class)
@Composable
fun VideoPlayer(modifier: Modifier) {
    val context = LocalContext.current

    val player = remember {
        val uri = Uri.Builder()
            .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
            .path(R.raw.intro_video.toString())
            .build()

        val media = MediaItem.Builder()
            .setUri(uri)
            .setMediaId("intro")
            .build()

        ExoPlayer.Builder(context).build().apply {
            setMediaItem(media)
            repeatMode = REPEAT_MODE_ONE
            prepare()
        }
    }

    val listener = remember {
        PlayerListener(player)
    }

    DisposableEffect(Unit) {
        player.addListener(listener)

        onDispose {
            player.stop()
            player.release()
            player.removeListener(listener)
        }
    }

    Box(modifier = modifier) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = {
                PlayerView(context).apply {
                    this.player = player
                    useController = false
                    resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                }
            }
        )
    }
}

@Composable
fun IntroTopBar(
    modifier: Modifier,
    language: String,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = Modifier,
            painter = painterResource(id = drawables.ic_movies_logo_white),
            contentDescription = "logo"
        )

        SelectLangButton(
            modifier = Modifier
                .clip(RoundedCornerShape(5.dp))
                .background(Color.White),
            language = language,
            onClick = onClick,
        )
    }
}

@Composable
fun SelectLangButton(
    modifier: Modifier,
    language: String,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier.clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.padding(
                top = MaterialTheme.padding.extraSmall,
                bottom = MaterialTheme.padding.extraSmall,
                start = MaterialTheme.padding.extraSmall,
                end = MaterialTheme.padding.extraSmall
            ),
            text = language.uppercase(),
            style = LocalTypography.current.extraSmall.body
        )
        Icon(
            modifier = Modifier.padding(
                top = MaterialTheme.padding.extraSmall,
                bottom = MaterialTheme.padding.extraSmall,
                end = MaterialTheme.padding.extraSmall
            ),
            painter = painterResource(id = drawables.ic_arrow_down),
            contentDescription = "select_language"
        )
    }
}

@Preview
@Composable
fun IntroTopBarPreview() {
    MviModularTheme {
        IntroTopBar(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(
                    horizontal = MaterialTheme.padding.large,
                    vertical = MaterialTheme.padding.medium
                ),
            language = "en",
            onClick = {}
        )
    }
}


@Composable
fun IntroContent(
    modifier: Modifier,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = MaterialTheme.padding.small,
                    end = MaterialTheme.padding.small,
                    bottom = MaterialTheme.padding.large
                ),
            text = stringResource(id = strings.intro_text_title),
            color = LocalColors.current.whiteLabelColorPrimary,
            style = LocalTypography.current.extraLarge.titleLarge,
        )

        Row(
            modifier = Modifier
                .align(Alignment.Start)
                .height(IntrinsicSize.Min)
                .padding(
                    bottom = 100.dp,
                    start = MaterialTheme.padding.small,
                )
        ) {
            VerticalDivider(
                modifier = Modifier
                    .width(1.dp)
                    .fillMaxHeight()
                    .padding(start = MaterialTheme.padding.small)
            )

            Text(
                modifier = Modifier.padding(start = MaterialTheme.padding.large),
                text = stringResource(id = strings.intro_text_description).trimIndent(),
                color = LocalColors.current.whiteLabelColorSecondary,
                style = LocalTypography.current.extraLarge.body,
            )
        }

        GradientButton(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding(),
            text = stringResource(id = strings.intro_lets_go_button_title),
            onClick = onClick,
        )
    }
}

@Preview
@Composable
fun IntroContentPreview() {
    MviModularTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            IntroContent(
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .align(Alignment.BottomStart)
                    .padding(
                        bottom = MaterialTheme.padding.extraLarge,
                        start = MaterialTheme.padding.large,
                        end = MaterialTheme.padding.large
                    ),
                onClick = {},
            )
        }
    }
}

private class PlayerListener(player: ExoPlayer) : Listener {
    private val p = WeakReference(player)
    override fun onPlaybackStateChanged(playbackState: Int) {
        if (playbackState == ExoPlayer.STATE_READY) p.get()?.play()
    }

    override fun onPlayerError(error: PlaybackException) {
        Log.e("Intro-Screen", error.message ?: "error")
    }
}