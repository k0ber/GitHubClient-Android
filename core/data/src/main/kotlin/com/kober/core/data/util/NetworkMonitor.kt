package com.kober.core.data.util

import kotlinx.coroutines.flow.Flow

/**
 * Utility for reporting app connectivity status
 */
interface NetworkMonitor {
    val isOnlineFlow: Flow<Boolean>
    fun isOnline(): Boolean
}
