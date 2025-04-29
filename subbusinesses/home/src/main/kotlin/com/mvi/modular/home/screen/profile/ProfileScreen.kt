package com.mvi.modular.home.screen.profile

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.mvi.modular.home.screen.profile.event.ProfileScreenEvent
import com.mvi.modular.home.screen.profile.state.ProfileScreenUiState
import com.mvi.modular.home.screen.profile.state.ProfileSettingItem
import com.mvi.modular.strings.core.strings
import com.mvi.modular.ui.compose.AnnotatedClickableText
import com.mvi.modular.ui.compose.Dialog
import com.mvi.modular.ui.compose.SelectLanguage
import com.mvi.modular.ui.core.drawables
import com.mvi.modular.ui.theme.DefaultRoundCornerShape
import com.mvi.modular.ui.theme.LocalColors
import com.mvi.modular.ui.theme.LocalPadding
import com.mvi.modular.ui.theme.LocalTypography
import com.mvi.modular.ui.theme.MviModularTheme
import com.mvi.modular.ui.theme.dimen
import com.mvi.modular.utils.context.restartApplication
import kotlinx.coroutines.flow.StateFlow


@Composable
internal fun ProfileScreen(
    state: StateFlow<ProfileScreenUiState>,
    onEvent: (ProfileScreenEvent) -> Unit,
) {
    val uiState by state.collectAsState()
    val activity = LocalActivity.current

    var loginBottomSheet by remember {
        mutableStateOf(false)
    }
    var langBottomSheet by remember {
        mutableStateOf(false)
    }
    var changeServerDialog by remember {
        mutableStateOf(false)
    }
    var errorDialog by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = true) {
        onEvent(ProfileScreenEvent.Load)
    }

    Column(
        modifier = Modifier.background(LocalColors.current.surfaceColor)
    ) {
        ProfileView(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            email = uiState.email,
        ) {
            loginBottomSheet = true
        }

        ProfileItemListView(
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp),
            currentLang = uiState.language,
            currentServer = uiState.server,
            items = uiState.items,
        ) { item ->
            when (item) {

                ProfileSettingItem.ChangeLanguage -> {
                    langBottomSheet = true
                }


                ProfileSettingItem.ChangeServer -> {
                    changeServerDialog = true
                }
            }
        }

        /* if (loginBottomSheet) {
             LoginScreen(
                 modifier = Modifier.fillMaxSize(),
                 onLoginResult = { success ->
                     if (success) {
                         onEvent(ProfileScreenEvent.GetProfile)
                     }
                     loginBottomSheet = false
                 }
             )
         }*/

        if (langBottomSheet) {
            SelectLanguage(
                languages = uiState.supportedLang,
            ) { lang ->
                if (lang != null) {
                    if (lang.languageCode != uiState.language) {
                        onEvent(ProfileScreenEvent.ChangeLanguage(lang))
                        activity?.recreate()
                    }
                }
                langBottomSheet = false
            }
        }
    }


    if (changeServerDialog) {
        Dialog(
            title = stringResource(id = strings.home_profile_change_server),
            message = stringResource(id = strings.home_profile_change_server_description),
            onDismissRequest = {
                changeServerDialog = false
            },
            onPositiveClicked = {
                onEvent(ProfileScreenEvent.ProfileSettingItemClicked(ProfileSettingItem.ChangeServer))
                changeServerDialog = false
            },
            onNegativeClicked = {
                changeServerDialog = false
            }
        )
    }

    if (errorDialog) {
        val errorInfo = uiState.errorInfo?.consume()
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
        errorDialog = uiState.errorInfo?.handled == false
    }

    if (uiState.serverChanged) {
        LocalContext.current.restartApplication()
    }
}


@Composable
private fun ProfileView(
    modifier: Modifier,
    email: String? = null,
    onClick: () -> Unit = {},
) {
    ConstraintLayout(modifier = modifier) {
        val (boxTop, boxBottom, title, avatar, account) = createRefs()
        val guide = createGuidelineFromTop(fraction = 0.7f)

        Box(
            modifier = Modifier
                .constrainAs(boxTop) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(guide)
                    height = Dimension.fillToConstraints
                    width = Dimension.matchParent
                }
                .background(
                    Brush.horizontalGradient(
                        colorStops = arrayOf(
                            0.0f to LocalColors.current.primaryGradiantEnd,
                            0.70f to LocalColors.current.primaryGradiantStart
                        )
                    )
                )
        )

        Box(
            modifier = Modifier
                .constrainAs(boxBottom) {
                    start.linkTo(parent.start)
                    top.linkTo(guide)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.fillToConstraints
                    width = Dimension.matchParent
                }
                .background(LocalColors.current.surfaceColor)
        )

        Text(
            modifier = Modifier
                .constrainAs(title) {
                    start.linkTo(avatar.start, margin = 5.dp)
                    end.linkTo(parent.end)
                    bottom.linkTo(avatar.top, margin = 25.dp)
                    width = Dimension.fillToConstraints
                },
            text = stringResource(id = strings.home_profile_title),
            style = LocalTypography.current.extraSmall.title1,
            color = LocalColors.current.blackLabelColorPrimary,
        )

        Box(
            modifier = Modifier
                .constrainAs(avatar) {
                    start.linkTo(parent.start, margin = 30.dp)
                    top.linkTo(guide)
                    bottom.linkTo(guide)
                    width = Dimension.value(80.dp)
                    height = Dimension.value(80.dp)
                }
                .shadow(4.dp, shape = CircleShape)
                .clip(CircleShape)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = drawables.ic_avatar),
                "LoginWithGoogle"
            )
        }

        if (email.isNullOrEmpty()) {
            AnnotatedClickableText(
                modifier = Modifier
                    .constrainAs(account) {
                        start.linkTo(avatar.start, margin = 5.dp)
                        top.linkTo(avatar.bottom, margin = 15.dp)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    },
                text = stringResource(id = strings.home_profile_title),
                placeholder = stringResource(id = strings.home_profile_title),
                textStyle = LocalTypography.current.extraLarge.body,
                annotatedColor = LocalColors.current.secondaryColor,
                onClick = onClick
            )
        } else {
            Text(
                modifier = Modifier
                    .constrainAs(account) {
                        start.linkTo(avatar.start, margin = 5.dp)
                        top.linkTo(avatar.bottom, margin = 15.dp)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    },
                text = email,
                style = LocalTypography.current.extraLarge.body,
                color = LocalColors.current.blackLabelColorPrimary,
            )
        }
    }
}

@Composable
private fun ProfileItemListView(
    modifier: Modifier,
    currentLang: String,
    currentServer: String,
    items: List<ProfileSettingItem>,
    onClick: (ProfileSettingItem) -> Unit = {},
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .verticalScroll(scrollState)
            .padding(horizontal = 15.dp)
    ) {
        repeat(items.size) {
            val item = items[it]
            val subtitle = when (item) {
                ProfileSettingItem.ChangeLanguage -> {
                    currentLang.uppercase()
                }

                ProfileSettingItem.ChangeServer -> {
                    currentServer
                }

            }

            ProfileItemView(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                icon = item.icon,
                title = stringResource(id = item.title),
                subtitle = subtitle
            ) {
                onClick(item)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileItemListViewPreview() {
    MviModularTheme {
        ProfileItemListView(
            modifier = Modifier.fillMaxWidth(),
            currentLang = "EN",
            currentServer = "prod",
            items = listOf(
                ProfileSettingItem.ChangeLanguage,
                ProfileSettingItem.ChangeServer,
            )
        )
    }
}

@Preview
@Composable
private fun ProfileViewPreview() {
    MviModularTheme {
        ProfileView(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            email = ""
        )
    }
}

@Composable
private fun ProfileItemView(
    modifier: Modifier,
    icon: Int,
    title: String,
    subtitle: String = "",
    onClick: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .border(
                MaterialTheme.dimen.borderWidth,
                LocalColors.current.blackLabelColorPrimary,
                DefaultRoundCornerShape
            )
            .clip(DefaultRoundCornerShape)
            .clickable { onClick() }
            .padding(
                horizontal = LocalPadding.current.medium,
                vertical = LocalPadding.current.medium
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier.size(25.dp),
            painter = painterResource(id = icon),
            contentDescription = null
        )

        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 10.dp),
            text = title,
            style = LocalTypography.current.medium.body,
            color = LocalColors.current.blackLabelColorPrimary,
        )

        Text(
            modifier = Modifier.padding(end = 10.dp),
            text = subtitle,
            style = LocalTypography.current.medium.body,
            color = LocalColors.current.blackLabelColorPrimary,
        )

        Icon(painter = painterResource(id = drawables.ic_arrow_right), contentDescription = null)
    }
}


@Preview(showBackground = true)
@Composable
fun ProfileItemViewPreview() {
    MviModularTheme {
        ProfileItemView(
            modifier = Modifier.fillMaxWidth(),
            icon = drawables.ic_localization,
            title = "My eSIMS",
            subtitle = "EN",
        )
    }
}


