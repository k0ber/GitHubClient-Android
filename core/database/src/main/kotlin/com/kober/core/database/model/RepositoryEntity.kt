package com.kober.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "repositories", foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("user_id"),
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["user_id"])]
)
class RepositoryEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("html_url") val htmlUrl: String,
    @ColumnInfo("description") val description: String?,
    @ColumnInfo("stars_count") val starsCount: Int,
    @ColumnInfo(name = "user_id") val userId: Int,
)
