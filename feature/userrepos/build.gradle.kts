plugins {
    alias(libs.plugins.ghclient.android.feature)
    alias(libs.plugins.ghclient.android.library.compose)
}

android {
    namespace = "com.kober.feature.userrepos"
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.composeutils)
    implementation(projects.core.coroutinesutils)

    implementation(libs.androidx.browser)
}
