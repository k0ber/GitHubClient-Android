package com.kober.core.database.di

import com.kober.core.database.GhClientDatabase
import com.kober.core.database.dao.ReposDao
import com.kober.core.database.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DaosModule {
    @Provides
    fun providesTopicsDao(
        database: GhClientDatabase,
    ): UserDao = database.userDao()

    @Provides
    fun providesNewsResourceDao(
        database: GhClientDatabase,
    ): ReposDao = database.reposDao()

}
