package com.kober.feature.searchhistory

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kober.component.GhClientButton
import com.kober.component.ThemePreviews
import com.kober.core.model.data.User
import com.kober.icons.GhClientIcons
import com.kober.shared.UserItem
import com.kober.theme.GhClientTheme
import com.kober.compose.utils.RunOnce

@Composable
fun SearchHistoryRoute(
    onUserClick: (User) -> Unit,
    onBackClick: () -> Unit,
    viewModel: SearchHistoryViewModel = hiltViewModel()
) {
    val screenState by viewModel.screenState.collectAsStateWithLifecycle()
    RunOnce { viewModel.loadHistory() }
    SearchHistoryScreen(
        screenState = screenState,
        onBackClick = onBackClick,
        onClearHistoryClick = viewModel::clearHistory,
        onUserClick = onUserClick,
        onRetryClick = viewModel::loadHistory
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchHistoryScreen(
    screenState: ScreenState,
    onClearHistoryClick: () -> Unit,
    onUserClick: (User) -> Unit,
    onBackClick: () -> Unit,
    onRetryClick: () -> Unit,
) {
    Column(
        Modifier
            .fillMaxSize()
    ) {
        CenterAlignedTopAppBar(
            title = { Text(text = stringResource(id = R.string.feature_searchhistory_title)) },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = GhClientIcons.Back,
                        contentDescription = stringResource(R.string.feature_searchhistory_clear),
                        tint = MaterialTheme.colorScheme.onSurface,
                    )
                }
            },
            actions = {
                IconButton(onClick = onClearHistoryClick) {
                    Icon(
                        imageVector = GhClientIcons.Delete,
                        contentDescription = stringResource(R.string.feature_searchhistory_clear),
                        tint = MaterialTheme.colorScheme.onSurface,
                    )
                }
            }
        )
        HorizontalDivider()
        when (screenState) {
            is ScreenState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.tertiary,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(48.dp)
                    )
                }
            }

            is ScreenState.Error -> {
                ErrorMessage(onRetryClick)
            }

            is ScreenState.Result -> {
                val users = screenState.savedUsers
                if (users.isEmpty()) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        Text(
                            text = stringResource(R.string.feature_searchhistory_empty),
                        )
                    }
                } else {
                    LazyColumn(
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        items(
                            count = users.size,
                            key = { users[it].id }
                        ) { index ->
                            val user = users[index]
                            UserItem(user, onClick = { onUserClick(user) })
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun ErrorMessage(onRetryClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(imageVector = GhClientIcons.Error, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stringResource(R.string.feature_searchhistory_generic_error),
            )
        }
        GhClientButton(
            onClick = onRetryClick,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(stringResource(R.string.feature_searchhistory_retry))
        }
    }
}

@ThemePreviews
@Composable
fun SearchHistoryScreenResultPreview() {
    GhClientTheme {
        Surface {
            SearchHistoryScreen(
                screenState = ScreenState.Result(List(6) { index ->
                    User(
                        id = index,
                        login = "user_$index",
                        avatarUrl = ""
                    )
                }),
                onClearHistoryClick = {},
                onUserClick = {},
                onBackClick = {},
                onRetryClick = {},
            )
        }
    }
}

@ThemePreviews
@Composable
fun SearchHistoryScreenEmptyResultPreview() {
    GhClientTheme {
        Surface {
            SearchHistoryScreen(
                screenState = ScreenState.Result(emptyList()),
                onClearHistoryClick = {},
                onUserClick = {},
                onBackClick = {},
                onRetryClick = {},
            )
        }
    }
}

@ThemePreviews
@Composable
fun SearchHistoryScreenLoadingPreview() {
    GhClientTheme {
        Surface {
            SearchHistoryScreen(
                screenState = ScreenState.Loading,
                onClearHistoryClick = {},
                onUserClick = {},
                onBackClick = {},
                onRetryClick = {},
            )
        }
    }
}

@ThemePreviews
@Composable
fun SearchHistoryScreenErrorPreview() {
    GhClientTheme {
        Surface {
            SearchHistoryScreen(
                screenState = ScreenState.Error(Throwable()),
                onClearHistoryClick = {},
                onUserClick = {},
                onBackClick = {},
                onRetryClick = {},
            )
        }
    }
}
