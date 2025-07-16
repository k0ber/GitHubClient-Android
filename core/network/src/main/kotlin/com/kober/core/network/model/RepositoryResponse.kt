package com.kober.core.network.model

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@InternalSerializationApi
@Serializable
class RepositoryResponse(
    val id: Int,
    val name: String,
    @SerialName("html_url")
    val htmlUrl: String,
    val description: String?,
    @SerialName("stargazers_count")
    val starsCount: Int
)
