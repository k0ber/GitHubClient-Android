package com.kober.compose.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun RunOnce(
    key: Any = Unit,
    action: () -> Unit
) {
    val hasBeenCalled = rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(key) {
        if (!hasBeenCalled.value) {
            action()
            hasBeenCalled.value = true
        }
    }
}
