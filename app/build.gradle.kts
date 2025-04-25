plugins {
    alias(libs.plugins.seraphim.android.application)
    alias(libs.plugins.seraphim.android.application.compose)
    alias(libs.plugins.seraphim.android.application.jacoco)
//    alias(libs.plugins.seraphim.android.application.firebase)
    alias(libs.plugins.seraphim.koin)
    alias(libs.plugins.baselineprofile)
    alias(libs.plugins.roborazzi)
    alias(libs.plugins.kotlin.serialization)
}
android {
    defaultConfig {
        applicationId = "com.seraphim.pokemon"
        versionCode = 8
        versionName = "0.1.2" // X.Y.Z; X = Major, Y = minor, Z = Patch level
        multiDexEnabled = true
    }
    packaging {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
    namespace = "com.seraphim.pokemon"
}
dependencies {
    implementation(project(":core:ui"))
    implementation(project(":shared"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.logback.android)
    implementation(libs.slf4j.api)

}