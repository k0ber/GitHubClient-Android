plugins {
    alias(libs.plugins.ghclient.jvm.library)
    alias(libs.plugins.ghclient.hilt)
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
}
