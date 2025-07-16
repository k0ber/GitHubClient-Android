package com.kober.core.model.data

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int,
    val login: String,
    val avatarUrl: String
)
