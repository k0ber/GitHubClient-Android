plugins {
    alias(libs.plugins.ghclient.android.library)
    alias(libs.plugins.ghclient.android.library.compose)
}

android {
    namespace = "com.kober.core.composeutils"
}

dependencies {
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.foundation)
}
