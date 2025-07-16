package com.kober.compose.utils

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer


fun Modifier.parallaxModifier(
    lazyListState: LazyListState,
    rate: Float = 0.6f
): Modifier = graphicsLayer {
    val hasVisibleInfo = lazyListState.layoutInfo.visibleItemsInfo.isNotEmpty()
    val isFirstItemVisible = lazyListState.firstVisibleItemIndex == 0

    alpha = if (hasVisibleInfo && isFirstItemVisible) {
        val firstItem = lazyListState.layoutInfo.visibleItemsInfo[0]
        val maxOffset = firstItem.size
        1f - (lazyListState.firstVisibleItemScrollOffset / maxOffset.toFloat()).coerceIn(0f, 1f)
    } else {
        0f
    }

    translationY = if (hasVisibleInfo && isFirstItemVisible) {
        lazyListState.firstVisibleItemScrollOffset * rate
    } else {
        0f
    }
}
