package com.kober.feature.userrepos.navigation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.kober.core.model.data.User
import com.kober.feature.userrepos.UserReposRoute
import com.kober.feature.userrepos.navigation.util.serializableType
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

@Serializable
data class UserReposDestination(val user: User) {
    companion object {
        val typeMap = mapOf(typeOf<User>() to serializableType<User>())

        fun from(savedStateHandle: SavedStateHandle) =
            savedStateHandle.toRoute<UserReposDestination>(typeMap)
    }
}

fun NavController.navigateToUserRepos(user: User) = navigate(route = UserReposDestination(user))

fun NavGraphBuilder.userReposScreen() {
    composable<UserReposDestination>(typeMap = UserReposDestination.typeMap) {
        UserReposRoute()
    }
}
