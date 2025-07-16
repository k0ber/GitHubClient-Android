plugins {
    alias(libs.plugins.ghclient.android.library)
}

android {
    namespace = "com.kober.core.coroutinesutils"
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
}
