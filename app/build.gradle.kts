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
    namespace = "com.seraphim.pokemon"
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
    kotlinOptions {
        freeCompilerArgs +=
            listOf(
                "-opt-in=com.google.accompanist.pager.ExperimentalPagerApi",
                "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
            )
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}
dependencies {
    implementation(project(":core:ui"))
    implementation(project(":shared"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.iconsExtended)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.koin.androidx.compose)
    implementation(libs.coil.kt.compose)
    implementation(libs.androidx.paging.compose)
    implementation(libs.logback.android)
    implementation(libs.slf4j.api)
    implementation(libs.androidx.palette.ktx)
    ksp(libs.destinations.ksp)
//    implementation(libs.destinations.core)
//    implementation(libs.destinations.bottom.sheet)
    implementation(libs.androidx.navigation3.runtime)
    implementation(libs.androidx.navigation3.ui)
    implementation(libs.androidx.lifecycle.viewmodel.navigation3)

    implementation(libs.process.phoenix)
}
baselineProfile {
    // Don't build on every iteration of a full assemble.
    // Instead enable generation directly for the release build variant.
    automaticGenerationDuringBuild = false

    // Make use of Dex Layout Optimizations via Startup Profiles
    dexLayoutOptimization = true
}
