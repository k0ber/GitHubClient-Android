package com.kober.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
class UserEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo("login") val login: String,
    @ColumnInfo("avatar_url") val avatarUrl: String
)
