plugins {
    alias(libs.plugins.ghclient.android.feature)
    alias(libs.plugins.ghclient.android.library.compose)

}

android {
    namespace = "com.kober.feature.searchhistory"
}

dependencies {
    implementation(projects.core.composeutils)
    implementation(projects.core.coroutinesutils)

    implementation(projects.core.data)
}
