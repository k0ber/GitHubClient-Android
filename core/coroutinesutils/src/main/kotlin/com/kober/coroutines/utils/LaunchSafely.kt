package com.kober.coroutines.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

inline fun CoroutineScope.launchSafely(
    crossinline block: suspend CoroutineScope.() -> Unit,
    crossinline onError: suspend (Throwable) -> Unit = {}
) = launch {
    try {
        block()
    } catch (e: Throwable) {
        if (e is CancellationException) throw e
        onError(e)
    }
}
