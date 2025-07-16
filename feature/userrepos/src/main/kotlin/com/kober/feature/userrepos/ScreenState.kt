package com.kober.feature.userrepos

import com.kober.core.model.data.Repository
import com.kober.core.model.data.User

sealed class ScreenState(val user: User) {
    class Loading(user: User) : ScreenState(user)
    class Result(user: User, val repos: List<Repository>) : ScreenState(user)
    class Error(user: User, val t: Throwable) : ScreenState(user)
}
