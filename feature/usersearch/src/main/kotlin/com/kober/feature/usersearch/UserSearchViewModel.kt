package com.kober.feature.usersearch

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kober.core.data.repository.UsersRepository
import com.kober.core.model.data.User
import com.kober.feature.usersearch.data.UserPagingRemoteSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

private const val SEARCH_QUERY_KEY = "SEARCH_QUERY_KEY"
private const val PAGING_SIZE = 30
private const val INITIAL_LOAD_SIZE = 60

@HiltViewModel
class UserSearchViewModel @Inject constructor(
    private val usersRepository: UsersRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val searchQuery = savedStateHandle.getStateFlow(key = SEARCH_QUERY_KEY, initialValue = "")

    val usersPagingData: StateFlow<PagingData<User>> = searchQuery
        .flatMapLatest { query ->
            Pager(
                config = PagingConfig(
                    pageSize = PAGING_SIZE,
                    enablePlaceholders = true,
                    initialLoadSize = INITIAL_LOAD_SIZE
                ),
                pagingSourceFactory = { UserPagingRemoteSource(usersRepository, query) }
            ).flow.cachedIn(viewModelScope)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = PagingData.empty()
        )

    fun onSearchQueryChanged(query: String) {
        savedStateHandle[SEARCH_QUERY_KEY] = query
    }
}
