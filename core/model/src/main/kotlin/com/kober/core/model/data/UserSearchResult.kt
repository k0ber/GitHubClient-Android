package com.kober.core.model.data

class UserSearchResult(
    val users: List<User>,
    val totalCount: Int,
    val incomplete: Boolean,
)
