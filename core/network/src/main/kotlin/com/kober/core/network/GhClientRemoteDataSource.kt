package com.kober.core.network

import com.kober.core.network.model.RepositoryResponse
import com.kober.core.network.model.UserSearchResponse
import kotlinx.serialization.InternalSerializationApi


@OptIn(InternalSerializationApi::class)
interface GhClientRemoteDataSource {

    suspend fun searchUsers(
        query: String,
        page: Int,
        perPage: Int
    ): UserSearchResponse

    suspend fun getUserRepos(username: String): List<RepositoryResponse>

}
