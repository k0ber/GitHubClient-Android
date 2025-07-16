package com.kober.core.network.di

import com.kober.core.network.GhClientRemoteDataSource
import com.kober.core.network.retrofit.GhClientRetrofitService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface DataSourceModule {

    @Binds
    fun binds(service: GhClientRetrofitService): GhClientRemoteDataSource

}
