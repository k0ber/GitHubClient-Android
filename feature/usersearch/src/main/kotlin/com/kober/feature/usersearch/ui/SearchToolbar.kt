package com.kober.feature.usersearch.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kober.component.ThemePreviews
import com.kober.feature.usersearch.R
import com.kober.icons.GhClientIcons
import com.kober.theme.GhClientTheme

@Composable
internal fun SearchToolbar(
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
    onHistoryClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        SearchTextField(
            onSearchQueryChanged = onSearchQueryChanged,
            searchQuery = searchQuery,
            modifier = Modifier
                .weight(1f)
        )
        IconButton(onClick = onHistoryClick) {
            Icon(
                imageVector = GhClientIcons.History,
                contentDescription = stringResource(R.string.feature_usersearch_history),
            )
        }
    }
}

@Composable
private fun SearchTextField(
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
    modifier: Modifier,
) {
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        if (searchQuery.isEmpty()) {
            focusRequester.requestFocus()
            keyboardController?.show()
        }
    }

    TextField(
        value = searchQuery,
        onValueChange = { onSearchQueryChanged(it) },
        modifier = modifier
            .fillMaxWidth()
            .focusRequester(focusRequester),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = GhClientIcons.Search,
                contentDescription = stringResource(id = R.string.feature_usersearch_title),
                tint = MaterialTheme.colorScheme.onSurface,
            )
        },
        trailingIcon = {
            if (searchQuery.isNotEmpty()) {
                IconButton(
                    onClick = {
                        onSearchQueryChanged("")
                        focusRequester.requestFocus()
                    },
                ) {
                    Icon(
                        imageVector = GhClientIcons.Close,
                        contentDescription = stringResource(
                            id = R.string.feature_usersearch_clear_search_text,
                        ),
                        tint = MaterialTheme.colorScheme.onSurface,
                    )
                }
            }
        },
        shape = RoundedCornerShape(32.dp),
    )
}

@ThemePreviews
@Composable
fun SearchToolbarPreview() {
    GhClientTheme {
        Surface {
            SearchToolbar(
                searchQuery = "abc",
                onSearchQueryChanged = {},
                onHistoryClick = {}
            )
        }
    }
}
