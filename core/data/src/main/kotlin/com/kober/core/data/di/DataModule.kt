package com.kober.core.data.di

import com.kober.core.data.repository.UserReposRepository
import com.kober.core.data.repository.UserReposRepositoryImpl
import com.kober.core.data.repository.UsersRepository
import com.kober.core.data.repository.UsersRepositoryImpl
import com.kober.core.data.util.ConnectivityManagerNetworkMonitor
import com.kober.core.data.util.NetworkMonitor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    internal abstract fun bindsUserRepository(
        usersRepository: UsersRepositoryImpl,
    ): UsersRepository

    @Binds
    internal abstract fun bindsReposRepository(
        usersReposRepository: UserReposRepositoryImpl,
    ): UserReposRepository

    @Binds
    internal abstract fun bindsNetworkMonitor(
        networkMonitor: ConnectivityManagerNetworkMonitor,
    ): NetworkMonitor

}
