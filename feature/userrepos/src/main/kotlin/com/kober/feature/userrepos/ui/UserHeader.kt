package com.kober.feature.userrepos.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import com.kober.component.DynamicAsyncImage
import com.kober.component.ThemePreviews
import com.kober.core.model.data.User
import com.kober.icons.GhClientIcons
import com.kober.theme.GhClientTheme

@Composable
fun UserHeader(modifier: Modifier = Modifier, user: User) {
    Box(
        modifier = modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.BottomStart
    ) {
        val placeholderPainter = rememberVectorPainter(GhClientIcons.Person)
        DynamicAsyncImage(
            imageUrl = user.avatarUrl,
            contentDescription = null,
            modifier = Modifier.matchParentSize(),
            imageModifier = Modifier.fillMaxSize(),
            placeholder = placeholderPainter,
        )
        Text(
            text = user.login,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(topEnd = 12.dp)
                )
                .padding(8.dp),
        )
    }
}

@ThemePreviews
@Composable
fun PreviewUserHeader() {
    GhClientTheme {
        Surface {
            UserHeader(
                modifier = Modifier.height(200.dp),
                user = User(
                    id = 0,
                    login = "UserLogin",
                    avatarUrl = ""
                )
            )
        }
    }
}
