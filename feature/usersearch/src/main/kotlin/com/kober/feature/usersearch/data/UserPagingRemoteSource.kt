package com.kober.feature.usersearch.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kober.core.data.repository.UsersRepository
import com.kober.core.model.data.User

class UserPagingRemoteSource(
    private val usersRepository: UsersRepository,
    private val query: String,
) : PagingSource<Int, User>() {

    override fun getRefreshKey(state: PagingState<Int, User>): Int? =
        state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1) ?: 1
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        return try {
            if (query.isBlank()) {
                return LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }

            val page = params.key ?: 1
            val response = usersRepository.searchUsers(
                query = query,
                page = page,
                perPage = params.loadSize
            )
            LoadResult.Page(
                data = response.users,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.users.size < params.loadSize) null else page + 1
            )
        } catch (e: Exception) {
            Log.w("UserPagingRemoteSource", e)
            LoadResult.Error(e)
        }
    }

}
