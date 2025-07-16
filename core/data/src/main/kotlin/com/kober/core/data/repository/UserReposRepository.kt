package com.kober.core.data.repository

import com.kober.core.model.data.Repository
import com.kober.core.model.data.User

interface UserReposRepository {

    suspend fun getUserRepos(user: User): List<Repository>

}
