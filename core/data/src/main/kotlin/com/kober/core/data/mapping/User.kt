package com.kober.core.data.mapping

import com.kober.core.database.model.UserEntity
import com.kober.core.model.data.User
import com.kober.core.network.model.UserResponse

fun List<UserEntity>.toModelFromEntity(): List<User> = map { userEntity ->
    with(userEntity) {
        User(
            id = id,
            login = login,
            avatarUrl = avatarUrl
        )
    }
}

fun List<UserResponse>.toModelFromResponse(): List<User> = map { userResponse ->
    with(userResponse) {
        User(
            id = id,
            login = login,
            avatarUrl = avatarUrl
        )
    }
}

fun User.toEntity(): UserEntity = UserEntity(id = id, login = login, avatarUrl = avatarUrl)
