package com.kober.core.database.di

import android.content.Context
import androidx.room.Room
import com.kober.core.database.GhClientDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {
    @Provides
    @Singleton
    fun providesGhClientDatabase(
        @ApplicationContext context: Context,
    ): GhClientDatabase = Room.databaseBuilder(
        context,
        GhClientDatabase::class.java,
        "gh-client-database",
    ).build()
}
