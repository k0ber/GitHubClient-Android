package com.kober.ghclient.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.kober.feature.searchhistory.navigation.navigateToSearchHistory
import com.kober.feature.searchhistory.navigation.searchHistoryScreen
import com.kober.feature.userrepos.navigation.navigateToUserRepos
import com.kober.feature.userrepos.navigation.userReposScreen
import com.kober.feature.usersearch.navigation.UserSearchDestination
import com.kober.feature.usersearch.navigation.userSearchScreen
import com.kober.ghclient.ui.AppState

@Composable
fun GhClientNavHost(
    appState: AppState,
) {
    val navController = appState.navController

    NavHost(
        navController = navController,
        startDestination = UserSearchDestination,
    ) {
        userSearchScreen(
            onUserClick = navController::navigateToUserRepos,
            onHistoryClick = navController::navigateToSearchHistory,
        )
        searchHistoryScreen(
            onUserClick = navController::navigateToUserRepos,
            onBackClick = navController::popBackStack,
        )
        userReposScreen()
    }
}
