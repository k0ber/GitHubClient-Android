plugins {
    alias(libs.plugins.ghclient.android.feature)
    alias(libs.plugins.ghclient.android.library.compose)
}

android {
    namespace = "com.kober.feature.usersearch"
}

dependencies {
    implementation(projects.core.data)
    implementation(libs.androidx.paging.compose)
    implementation(libs.androidx.paging.runtime)
}
