plugins {
    alias(libs.plugins.ghclient.android.library)
    alias(libs.plugins.ghclient.hilt)
    id("kotlinx-serialization")
}

android {
    namespace = "com.kober.core.data"
}

dependencies {
    api(projects.core.common)
    api(projects.core.database)
    api(projects.core.network)
}
