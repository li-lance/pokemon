plugins{
    alias(libs.plugins.seraphim.android.library)
    alias(libs.plugins.seraphim.android.library.compose)
}


android {
    namespace = "com.seraphim.domain.scaffold.ui"
}
dependencies {
    implementation(project(":core:ui"))
    implementation(libs.androidx.compose.material.iconsExtended)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.ui.util)
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.tooling.preview)
}