plugins{
    alias(libs.plugins.seraphim.android.library)
    alias(libs.plugins.seraphim.android.library.compose)
}


android {
    namespace = "com.seraphim.core.ui"
    kotlinOptions {
        freeCompilerArgs +=
            listOf(
                "-opt-in=com.google.accompanist.pager.ExperimentalPagerApi",
                "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
            )
    }
}
dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.iconsExtended)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.ui.util)
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.tooling.preview)
}