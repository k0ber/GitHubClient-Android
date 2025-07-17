package com.kober.feature.userrepos

import android.content.Context
import android.net.Uri
import androidx.annotation.ColorInt
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kober.component.ThemePreviews
import com.kober.compose.utils.RunOnce
import com.kober.compose.utils.parallaxModifier
import com.kober.core.model.data.Repository
import com.kober.core.model.data.User
import com.kober.feature.userrepos.ui.RepositoryItem
import com.kober.feature.userrepos.ui.UserHeader
import com.kober.theme.GhClientTheme

@Composable
internal fun UserReposRoute(
    viewModel: UserReposViewModel = hiltViewModel()
) {
    val screenState by viewModel.screenState.collectAsStateWithLifecycle()
    RunOnce {
        viewModel.loadUserRepos()
    }
    UserReposScreen(
        screenState = screenState,
        onRetry = viewModel::loadUserRepos
    )
}

@Composable
internal fun UserReposScreen(
    screenState: ScreenState,
    onRetry: () -> Unit
) {

    val headerHeight = 200.dp
    val lazyListState = rememberLazyListState()

    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        val placeHolderHeight = this.maxHeight - headerHeight

        LazyColumn(
            state = lazyListState,
            modifier = Modifier.fillMaxSize()
        ) {
            item(key = "user_${screenState.user.id}") {
                UserHeader(
                    user = screenState.user,
                    modifier = Modifier
                        .height(headerHeight)
                        .fillMaxWidth()
                        .parallaxModifier(lazyListState)
                )
            }

            when (screenState) {
                is ScreenState.Result -> {
                    val userRepos = screenState.repos
                    if (userRepos.isEmpty()) {
                        item { EmptyReposPlaceholder(placeHolderHeight) }
                    } else {
                        items(count = userRepos.size, key = { userRepos[it].id }) { index ->
                            val repo = userRepos[index]
                            val context = LocalContext.current
                            val uri = repo.htmlUrl.toUri()
                            val backgroundColor = MaterialTheme.colorScheme.background.toArgb()
                            RepositoryItem(
                                repository = repo,
                                onClick = { showBrowserWindow(context, uri, backgroundColor) },
                            )
                        }
                    }
                }

                is ScreenState.Error -> {
                    item { ErrorPlaceholder(placeHolderHeight, onRetry) }
                }

                is ScreenState.Loading -> {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(placeHolderHeight),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun EmptyReposPlaceholder(contentHeight: Dp) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(contentHeight),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.feature_userrepos_empty_result),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
private fun ErrorPlaceholder(contentHeight: Dp, onRetry: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(contentHeight),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.feature_userrepos_generic_error),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.error
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onRetry) {
            Text(text = stringResource(R.string.feature_userrepos_retry))
        }
    }
}

private fun showBrowserWindow(context: Context, uri: Uri, @ColorInt toolbarColor: Int) {
    val customTabBarColor = CustomTabColorSchemeParams.Builder()
        .setToolbarColor(toolbarColor).build()
    val customTabsIntent = CustomTabsIntent.Builder()
        .setDefaultColorSchemeParams(customTabBarColor)
        .build()

    customTabsIntent.launchUrl(context, uri)
}

@ThemePreviews
@Composable
fun UserWithSomeReposPreview() {
    GhClientTheme {
        Surface {
            UserReposScreen(
                screenState = ScreenState.Result(
                    user = User(1, "User", ""),
                    repos = List(5) { index ->
                        Repository(
                            id = index,
                            name = "Repo_$index",
                            htmlUrl = "",
                            description = "",
                            starsCount = index * 100
                        )
                    }
                ),
                onRetry = {}
            )
        }
    }
}

@ThemePreviews
@Composable
fun UserWithoutReposPreview() {
    GhClientTheme {
        Surface {
            UserReposScreen(
                screenState = ScreenState.Result(
                    user = User(1, "User", ""),
                    repos = emptyList()
                ),
                onRetry = {}
            )
        }
    }
}

@ThemePreviews
@Composable
fun UserReposLoadingPreview() {
    GhClientTheme {
        Surface {
            UserReposScreen(
                screenState = ScreenState.Loading(
                    user = User(1, "User", ""),
                ),
                onRetry = {}
            )
        }
    }
}

@ThemePreviews
@Composable
fun UserReposErrorPreview() {
    GhClientTheme {
        Surface {
            UserReposScreen(
                screenState = ScreenState.Error(
                    user = User(1, "User", ""),
                    t = IllegalStateException()
                ),
                onRetry = {}
            )
        }
    }
}
