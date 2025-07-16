package com.kober.feature.usersearch.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.kober.core.model.data.User
import com.kober.feature.usersearch.UserSearchRoute
import kotlinx.serialization.Serializable

@Serializable
data object UserSearchDestination

fun NavGraphBuilder.userSearchScreen(
    onUserClick: (User) -> Unit,
    onHistoryClick: () -> Unit,
) {
    composable<UserSearchDestination> {
        UserSearchRoute(
            onUserClick = onUserClick,
            onHistoryClick = onHistoryClick,
        )
    }
}
