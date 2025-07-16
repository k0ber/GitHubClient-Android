package com.kober.core.network.model

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@InternalSerializationApi
@Serializable
class UserResponse(
    val id: Int,
    val login: String,
    @SerialName("avatar_url")
    val avatarUrl: String
)
