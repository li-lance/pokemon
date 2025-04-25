plugins{
    alias(libs.plugins.seraphim.android.library)
    alias(libs.plugins.seraphim.android.library.compose)
}


android {
    namespace = "com.seraphim.core.permission"
}
dependencies {
    implementation(libs.accompanist.permissions)
}