package com.kober.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kober.core.database.dao.ReposDao
import com.kober.core.database.dao.UserDao
import com.kober.core.database.model.RepositoryEntity
import com.kober.core.database.model.UserEntity


@Database(
    entities = [
        UserEntity::class,
        RepositoryEntity::class,
    ],
    version = 1,
    autoMigrations = [
    ],
    exportSchema = true,
)
internal abstract class GhClientDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun reposDao(): ReposDao
}
