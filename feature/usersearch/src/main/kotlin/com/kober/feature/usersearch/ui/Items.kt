package com.kober.feature.usersearch.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kober.feature.usersearch.R
import com.kober.component.GhClientButton
import com.kober.component.ThemePreviews
import com.kober.theme.GhClientTheme

@Composable
internal fun UserPlaceholder() {
    Box(
        Modifier
            .fillMaxWidth()
            .height(64.dp)
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .align(Alignment.Center)
                .size(48.dp)
        )
    }
}

@Composable
internal fun UserLoadingError(modifier: Modifier = Modifier, onRetry: () -> Unit) {
    ListItem(
        headlineContent = {
            Text(
                text = stringResource(R.string.feature_usersearch_loading_error),
            )
        },
        trailingContent = {
            GhClientButton(
                onClick = onRetry,
                text = { Text(stringResource(R.string.feature_usersearch_retry)) })
        },
        modifier = modifier
            .clickable(onClick = onRetry),
    )
}

@ThemePreviews
@Composable
private fun UserPlaceholderPreview() {
    GhClientTheme {
        Surface {
            UserPlaceholder()
        }
    }
}

@ThemePreviews
@Composable
private fun UserLoadingErrorPreview() {
    GhClientTheme {
        Surface {
            UserLoadingError {}
        }
    }
}