package com.kober.feature.searchhistory

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kober.core.data.repository.UsersRepository
import com.kober.coroutines.utils.launchSafely
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SearchHistoryViewModel @Inject constructor(
    private val usersRepository: UsersRepository,
) : ViewModel() {

    private val _screenState = MutableStateFlow<ScreenState>(ScreenState.Loading)
    val screenState: StateFlow<ScreenState> = _screenState

    fun loadHistory() {
        viewModelScope.launchSafely(
            block = {
                _screenState.emit(ScreenState.Loading)
                val savedUsers = usersRepository.getSavedUsers()
                _screenState.emit(ScreenState.Result(savedUsers))
            },
            onError = {
                _screenState.emit(ScreenState.Error(it))
            }
        )
    }

    fun clearHistory() {
        viewModelScope.launchSafely(
            block = {
                _screenState.emit(ScreenState.Loading)
                usersRepository.removeSavedUsers()
                _screenState.emit(ScreenState.Result(emptyList()))
            },
            onError = {
                Log.w("SearchHistoryViewModel", it)
                _screenState.emit(ScreenState.Error(it))
            }
        )
    }

}
