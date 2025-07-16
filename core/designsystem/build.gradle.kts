plugins {
    alias(libs.plugins.ghclient.android.library)
    alias(libs.plugins.ghclient.android.library.compose)
}

android {
    namespace = "com.kober.core.designsystem"
}

dependencies {
    implementation(projects.core.model)

    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.foundation.layout)
    api(libs.androidx.compose.material.iconsExtended)
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.material3.adaptive)
    api(libs.androidx.compose.material3.navigationSuite)
    api(libs.androidx.compose.runtime)
    api(libs.androidx.compose.runtime.saveable)
    api(libs.androidx.compose.ui.util)

    implementation(libs.coil.kt.compose)
}
