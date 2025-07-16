package com.kober.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kober.core.database.model.RepositoryEntity

@Dao
interface ReposDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepositories(repositories: List<RepositoryEntity>)

    @Query("DELETE FROM repositories WHERE user_id = :userId")
    suspend fun deleteRepositoriesForUser(userId: Int)

    @Query("SELECT * FROM repositories WHERE user_id = :userId")
    suspend fun getRepositoriesForUser(userId: Int): List<RepositoryEntity>

}
