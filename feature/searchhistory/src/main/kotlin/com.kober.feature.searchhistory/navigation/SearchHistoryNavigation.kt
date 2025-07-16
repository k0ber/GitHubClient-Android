package com.kober.feature.searchhistory.navigation

import com.kober.feature.searchhistory.SearchHistoryRoute
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.kober.core.model.data.User
import kotlinx.serialization.Serializable

@Serializable
data object SearchHistoryDestination

fun NavController.navigateToSearchHistory() = navigate(route = SearchHistoryDestination)

fun NavGraphBuilder.searchHistoryScreen(
    onUserClick: (User) -> Unit,
    onBackClick: () -> Unit,
) {
    composable<SearchHistoryDestination> {
        SearchHistoryRoute(onUserClick = onUserClick, onBackClick = onBackClick)
    }
}
