package com.kober.core.data.mapping

import com.kober.core.database.model.RepositoryEntity
import com.kober.core.network.model.RepositoryResponse
import com.kober.core.model.data.Repository
import com.kober.core.model.data.User

fun List<RepositoryResponse>.toModelFromResponse(): List<Repository> = map { repositoryResponse ->
    with(repositoryResponse) {
        Repository(
            id = id,
            name = name,
            htmlUrl = htmlUrl,
            description = description,
            starsCount = starsCount
        )
    }
}

fun List<RepositoryEntity>.toModelFromEntity(): List<Repository> =
    map { repositoryEntity ->
        with(repositoryEntity) {
            Repository(
                id = id,
                name = name,
                htmlUrl = htmlUrl,
                description = description,
                starsCount = starsCount
            )
        }
    }

fun List<RepositoryResponse>.toEntity(user: User): List<RepositoryEntity> =
    map { repositoryResponse ->
        with(repositoryResponse) {
            RepositoryEntity(
                id = id,
                name = name,
                htmlUrl = htmlUrl,
                description = description,
                starsCount = starsCount,
                userId = user.id
            )
        }
    }
