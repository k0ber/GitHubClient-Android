package com.kober.core.data.mapping

import com.kober.core.model.data.UserSearchResult
import com.kober.core.network.model.UserSearchResponse

fun UserSearchResponse.toModelFromResponse(): UserSearchResult = UserSearchResult(
    users = items.toModelFromResponse(),
    totalCount = totalCount,
    incomplete = incomplete
)
