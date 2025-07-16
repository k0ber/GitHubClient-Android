package com.kober.core.data.repository

import com.kober.core.data.mapping.toModelFromEntity
import com.kober.core.data.mapping.toModelFromResponse
import com.kober.core.database.dao.UserDao
import com.kober.core.dispatchers.Dispatcher
import com.kober.core.dispatchers.GhClientDispatchers.IO
import com.kober.core.model.data.User
import com.kober.core.model.data.UserSearchResult
import com.kober.core.network.GhClientRemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class UsersRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val remoteDataSource: GhClientRemoteDataSource,
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
) : UsersRepository {

    override suspend fun getSavedUsers(): List<User> = withContext(ioDispatcher) {
        userDao.getAllUsers().toModelFromEntity()
    }

    override suspend fun removeSavedUsers() = withContext(ioDispatcher) {
        userDao.deleteAllUsers()
    }

    override suspend fun searchUsers(query: String, page: Int, perPage: Int): UserSearchResult =
        withContext(ioDispatcher) {
            remoteDataSource.searchUsers(query, page, perPage).toModelFromResponse()
        }

}
