package com.kober.shared

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.kober.component.DynamicAsyncImage
import com.kober.component.ThemePreviews
import com.kober.core.model.data.User
import com.kober.icons.GhClientIcons
import com.kober.theme.GhClientTheme
import com.kober.theme.LocalTintTheme

@Composable
fun UserItem(
    user: User,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
) {
    ListItem(
        leadingContent = {
            AvatarIcon(user.avatarUrl, iconModifier.size(48.dp))
        },
        headlineContent = {
            Text(
                text = user.login,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        modifier = modifier
            .clickable(onClick = onClick),
    )
}

@Composable
private fun AvatarIcon(avatarUrl: String, modifier: Modifier = Modifier) {
    val placeholderModifier = modifier
        .padding(4.dp)
        .size(48.dp)
    if (avatarUrl.isEmpty()) {
        Icon(
            modifier = placeholderModifier,
            imageVector = GhClientIcons.Person,
            contentDescription = null,
            tint = LocalTintTheme.current.iconTint
        )
    } else {
        val placeholderPainter = rememberVectorPainter(GhClientIcons.Person)
        DynamicAsyncImage(
            imageUrl = avatarUrl,
            contentDescription = null,
            modifier = modifier,
            imageModifier = placeholderModifier,
            placeholder = placeholderPainter,
        )
    }
}

@ThemePreviews
@Composable
private fun UserItemWithNoAvatarUrlPreview() {
    GhClientTheme {
        Surface {
            UserItem(
                User(0, "UserName", ""),
                onClick = { },
            )
        }
    }
}

@ThemePreviews
@Composable
private fun UserItemWithAvatarUrlPreview() {
    GhClientTheme {
        Surface {
            UserItem(
                User(0, "UserName", "test"),
                onClick = { },
            )
        }
    }
}

@ThemePreviews
@Composable
private fun UserItemWithLongNamePreview() {
    GhClientTheme {
        Surface {
            UserItem(
                User(0, "Very very very very very very very very very very long user name", "fds"),
                onClick = { },
            )
        }
    }
}
