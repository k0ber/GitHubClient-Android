package com.kober.core.network.model

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@InternalSerializationApi
@Serializable
class UserSearchResponse(
    val items: List<UserResponse>,
    @SerialName("total_count")
    val totalCount: Int,
    @SerialName("incomplete_results")
    val incomplete: Boolean
)
