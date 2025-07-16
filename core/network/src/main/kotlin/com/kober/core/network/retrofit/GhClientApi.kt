package com.kober.core.network.retrofit

import com.kober.core.network.model.RepositoryResponse
import com.kober.core.network.model.UserSearchResponse
import kotlinx.serialization.InternalSerializationApi
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

@OptIn(InternalSerializationApi::class)
interface GhClientApi {

    @GET("search/users")
    suspend fun searchUsers(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): UserSearchResponse

    @GET("users/{username}/repos")
    suspend fun getUserRepos(
        @Path("username") username: String
    ): List<RepositoryResponse>

}
