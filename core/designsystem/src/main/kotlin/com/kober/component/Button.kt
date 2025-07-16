package com.kober.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kober.icons.GhClientIcons
import com.kober.theme.GhClientTheme

@Composable
fun GhClientButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.onBackground,
        ),
        contentPadding = contentPadding,
        content = content,
    )
}

@Composable
fun GhClientButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: @Composable () -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
) {
    GhClientButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        contentPadding = if (leadingIcon != null) {
            ButtonDefaults.ButtonWithIconContentPadding
        } else {
            ButtonDefaults.ContentPadding
        },
    ) {
        GhClientButtonContent(
            text = text,
            leadingIcon = leadingIcon,
        )
    }
}

@Composable
private fun GhClientButtonContent(
    text: @Composable () -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
) {
    if (leadingIcon != null) {
        Box(Modifier.sizeIn(maxHeight = ButtonDefaults.IconSize)) {
            leadingIcon()
        }
    }
    Box(
        Modifier
            .padding(
                start = if (leadingIcon != null) ButtonDefaults.IconSpacing else 0.dp,
            ),
    ) {
        text()
    }
}

@ThemePreviews
@Composable
fun ButtonPreview() {
    GhClientTheme {
        GhClientBackground(modifier = Modifier.size(150.dp, 50.dp)) {
            GhClientButton(
                onClick = {},
                text = { Text("Test button") },
                leadingIcon = {
                    Icon(
                        imageVector = GhClientIcons.Close,
                        contentDescription = null
                    )
                },
            )
        }
    }
}

@ThemePreviews
@Composable
fun ButtonWithNoIconPreview() {
    GhClientTheme {
        GhClientBackground(modifier = Modifier.size(150.dp, 50.dp)) {
            GhClientButton(
                onClick = {},
                text = { Text("Test button") },
                leadingIcon = null,
            )
        }
    }
}
