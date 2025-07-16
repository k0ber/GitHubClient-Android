package com.kober.core.dispatchers

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val ghClientDispatchers: GhClientDispatchers)

enum class GhClientDispatchers {
    Default,
    IO,
}
