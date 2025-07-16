package com.kober.feature.usersearch

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.kober.core.model.data.User
import com.kober.feature.usersearch.ui.SearchToolbar
import com.kober.feature.usersearch.ui.UserLoadingError
import com.kober.feature.usersearch.ui.UserPlaceholder
import com.kober.shared.UserItem

@Composable
internal fun UserSearchRoute(
    onUserClick: (User) -> Unit,
    onHistoryClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UserSearchViewModel = hiltViewModel()
) {
    val usersPagingData = viewModel.usersPagingData.collectAsLazyPagingItems()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()

    UserSearchScreen(
        modifier = modifier,
        searchQuery = searchQuery,
        usersPagingData = usersPagingData,
        onSearchQueryChanged = viewModel::onSearchQueryChanged,
        onUserClick = onUserClick,
        onHistoryClick = onHistoryClick
    )
}

@Composable
internal fun UserSearchScreen(
    modifier: Modifier = Modifier,
    searchQuery: String,
    usersPagingData: LazyPagingItems<User>,
    onSearchQueryChanged: (String) -> Unit,
    onUserClick: (User) -> Unit,
    onHistoryClick: () -> Unit
) {
    Column(modifier = modifier) {
        SearchToolbar(
            onSearchQueryChanged = onSearchQueryChanged,
            searchQuery = searchQuery,
            onHistoryClick = onHistoryClick
        )
        when {
            searchQuery.isBlank() -> {
                Text(
                    text = stringResource(id = R.string.feature_usersearch_initial_message),
                    modifier = Modifier.fillMaxSize(),
                    textAlign = TextAlign.Center
                )
            }

            searchQuery.isNotBlank() && usersPagingData.isResultEmpty() ->
                Text(
                    text = stringResource(id = R.string.feature_usersearch_nothing_found),
                    modifier = Modifier.fillMaxSize(),
                    textAlign = TextAlign.Center
                )

            else -> {
                SearchResultList(usersPagingData, onUserClick)
            }
        }

    }
}

@Composable
private fun SearchResultList(
    userPagingItems: LazyPagingItems<User>,
    onUserClick: (User) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val lazyListState = rememberLazyListState()

    LaunchedEffect(lazyListState.isScrollInProgress) {
        if (lazyListState.isScrollInProgress) {
            focusManager.clearFocus()
        }
    }

    LazyColumn(
        state = lazyListState,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxSize(),
    ) {
        items(
            count = userPagingItems.itemCount,
            key = userPagingItems.itemKey { it.id }
        ) { index ->
            userPagingItems[index]?.let { user ->
                UserItem(user, onClick = { onUserClick(user) })
            }
        }
        if (userPagingItems.loadState.append is LoadState.Loading ||
            userPagingItems.loadState.refresh is LoadState.Loading
        ) {
            item { UserPlaceholder() }
        }
        if (userPagingItems.loadState.append is LoadState.Error ||
            userPagingItems.loadState.refresh is LoadState.Error
        ) {
            item { UserLoadingError(onRetry = userPagingItems::retry) }
        }
    }
}

private fun <T : Any> LazyPagingItems<T>.isResultEmpty(): Boolean =
    loadState.refresh != LoadState.Loading && loadState.refresh !is LoadState.Error && itemCount == 0
