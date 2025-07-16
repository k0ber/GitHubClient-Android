package com.kober.feature.userrepos

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kober.core.data.repository.UserReposRepository
import com.kober.coroutines.utils.launchSafely
import com.kober.feature.userrepos.navigation.UserReposDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class UserReposViewModel @Inject constructor(
    private val userReposRepository: UserReposRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val user = UserReposDestination.from(savedStateHandle).user
    private val _screenState = MutableStateFlow<ScreenState>(ScreenState.Loading(user))
    val screenState: StateFlow<ScreenState> = _screenState

    fun loadUserRepos() {
        viewModelScope.launchSafely(
            block = {
                _screenState.emit(ScreenState.Loading(user))
                val repos = userReposRepository.getUserRepos(user)
                val sorted = repos.sortedByDescending { it.starsCount }
                _screenState.emit(ScreenState.Result(user, sorted))
            },
            onError = {
                Log.w("UserReposViewModel", it)
                _screenState.emit(ScreenState.Error(user, it))
            }
        )
    }

}
