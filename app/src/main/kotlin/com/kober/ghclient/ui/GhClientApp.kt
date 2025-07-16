package com.kober.ghclient.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kober.component.GhClientBackground
import com.kober.ghclient.R
import com.kober.ghclient.navigation.GhClientNavHost

@Composable
fun GhClientApp(
    appState: AppState,
    modifier: Modifier = Modifier,
) {
    GhClientBackground(modifier = modifier) {
        val isOffline by appState.isOffline.collectAsStateWithLifecycle()

        Scaffold(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onBackground,
            contentWindowInsets = WindowInsets.safeDrawing,
        ) { padding ->
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .consumeWindowInsets(padding)
            ) {
                if (isOffline) {
                    OfflineCard()
                }
                GhClientNavHost(
                    appState = appState,
                )
            }
        }
    }
}

@Composable
private fun OfflineCard() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        color = MaterialTheme.colorScheme.errorContainer
    ) {
        Text(
            modifier = Modifier.padding(4.dp),
            text = stringResource(R.string.not_connected),
            style = MaterialTheme.typography.labelLarge,
            textAlign = TextAlign.Center
        )
    }
}
