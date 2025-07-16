package com.kober.feature.searchhistory

import com.kober.core.model.data.User

sealed interface ScreenState {
    data object Loading : ScreenState
    class Result(val savedUsers: List<User>) : ScreenState
    class Error(val t: Throwable) : ScreenState
}
