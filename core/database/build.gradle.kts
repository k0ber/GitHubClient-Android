plugins {
    alias(libs.plugins.ghclient.android.library)
    alias(libs.plugins.ghclient.android.room)
    alias(libs.plugins.ghclient.hilt)
}

android {
    namespace = "com.kober.core.database"
}

dependencies {
    api(projects.core.model)
}
