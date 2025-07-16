package com.kober.core.network.retrofit

import com.kober.core.network.GhClientRemoteDataSource
import com.kober.core.network.model.RepositoryResponse
import com.kober.core.network.model.UserSearchResponse
import dagger.Lazy
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

private const val BASE_URL = "https://api.github.com/"

@OptIn(InternalSerializationApi::class)
@Singleton
internal class GhClientRetrofitService @Inject constructor(
    networkJson: Json,
    okhttpCallFactory: Lazy<Call.Factory>,
) : GhClientRemoteDataSource {

    private val networkApi = Retrofit.Builder()
            .baseUrl(BASE_URL)
            // We use callFactory lambda here with dagger.Lazy<Call.Factory>
            // to prevent initializing OkHttp on the main thread.
            .callFactory { okhttpCallFactory.get().newCall(it) }
            .addConverterFactory(
                networkJson.asConverterFactory("application/json".toMediaType()),
            )
            .build()
            .create(GhClientApi::class.java)

    override suspend fun searchUsers(query: String, page: Int, perPage: Int): UserSearchResponse {
        return networkApi.searchUsers(query, page, perPage)
    }

    override suspend fun getUserRepos(username: String): List<RepositoryResponse> {
        return networkApi.getUserRepos(username)
    }

}
