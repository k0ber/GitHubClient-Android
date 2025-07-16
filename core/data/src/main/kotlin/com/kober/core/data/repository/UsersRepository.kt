package com.kober.core.data.repository

import com.kober.core.model.data.User
import com.kober.core.model.data.UserSearchResult

interface UsersRepository {

    suspend fun getSavedUsers(): List<User>

    suspend fun removeSavedUsers()

    suspend fun searchUsers(query: String, page: Int, perPage: Int): UserSearchResult

}
