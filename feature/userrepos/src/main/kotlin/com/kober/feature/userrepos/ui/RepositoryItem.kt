package com.kober.feature.userrepos.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.kober.component.ThemePreviews
import com.kober.core.model.data.Repository
import com.kober.feature.userrepos.ui.util.toShortenedFormat
import com.kober.icons.GhClientIcons
import com.kober.theme.GhClientTheme

@Composable
fun RepositoryItem(
    repository: Repository,
    onClick: (Repository) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick(repository) },
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = repository.name,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                repository.description?.let {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = repository.starsCount.toShortenedFormat(),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier
            )
            Icon(
                imageVector = GhClientIcons.Star,
                contentDescription = null,
                modifier = Modifier,
                tint = MaterialTheme.colorScheme.tertiary
            )
        }
    }
}

@ThemePreviews
@Composable
fun RepositoryItemPreview() {
    GhClientTheme {
        Surface {
            RepositoryItem(
                repository = Repository(
                    id = 1,
                    name = "Repository name",
                    htmlUrl = "some_url",
                    description = "some description",
                    starsCount = 2875
                ),
                onClick = {}
            )
        }
    }
}

@ThemePreviews
@Composable
fun RepositoryItemNoDescriptionPreview() {
    GhClientTheme {
        Surface {
            RepositoryItem(
                repository = Repository(
                    id = 1,
                    name = "Repository name",
                    htmlUrl = "some_url",
                    description = null,
                    starsCount = 0
                ),
                onClick = {}
            )
        }
    }
}


@ThemePreviews
@Composable
fun RepositoryItemLongTextPreview() {
    GhClientTheme {
        Surface {
            RepositoryItem(
                repository = Repository(
                    id = 1,
                    name = "Very long repository name very very long so long even longer but in the end it doesn't even matter",
                    htmlUrl = "some_url",
                    description = "Very long repository description with a lot of details long text very very long text with a lot of details and useful info",
                    starsCount = 10500
                ),
                onClick = {}
            )
        }
    }
}

