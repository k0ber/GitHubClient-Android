package com.kober.feature.userrepos.ui.util

import android.annotation.SuppressLint

@SuppressLint("DefaultLocale")
fun Int.toShortenedFormat(): String = when (this) {
    in 1_000_000..Int.MAX_VALUE -> "%.1fM".format(this / 1_000_000f)
    in 1_000..999_999 -> "%.1fK".format(this / 1_000f)
    else -> this.toString()
}
