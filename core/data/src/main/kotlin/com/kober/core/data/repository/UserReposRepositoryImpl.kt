package com.kober.core.data.repository

import com.kober.core.data.mapping.toEntity
import com.kober.core.data.mapping.toModelFromEntity
import com.kober.core.data.mapping.toModelFromResponse
import com.kober.core.data.util.NetworkMonitor
import com.kober.core.database.dao.ReposDao
import com.kober.core.database.dao.UserDao
import com.kober.core.dispatchers.Dispatcher
import com.kober.core.dispatchers.GhClientDispatchers.IO
import com.kober.core.model.data.Repository
import com.kober.core.model.data.User
import com.kober.core.network.GhClientRemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserReposRepositoryImpl @Inject constructor(
    private val networkMonitor: NetworkMonitor,
    private val userDao: UserDao,
    private val reposDao: ReposDao,
    private val remoteDataSource: GhClientRemoteDataSource,
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
) : UserReposRepository {

    override suspend fun getUserRepos(user: User): List<Repository> =
        withContext(ioDispatcher) {
            if (networkMonitor.isOnline()) {
                val reposResponse = remoteDataSource.getUserRepos(user.login)
                userDao.insertUser(user.toEntity())
                reposDao.deleteRepositoriesForUser(user.id)
                reposDao.insertRepositories(reposResponse.toEntity(user))
                reposResponse.toModelFromResponse()
            } else {
                reposDao.getRepositoriesForUser(user.id).toModelFromEntity()
            }
        }

}
