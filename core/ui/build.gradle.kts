plugins{
    alias(libs.plugins.seraphim.android.library)
    alias(libs.plugins.seraphim.android.library.compose)
}


android {
    namespace = "com.seraphim.core.ui"
}
dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.ui.util)
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.tooling.preview)
}