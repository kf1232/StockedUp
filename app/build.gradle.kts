plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.fink.stockedup"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.fink.stockedup"
        minSdk = 30
        targetSdk = 35
        versionCode = 25022701
        versionName = "1.0.25022701"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.paging.runtime)
    implementation(libs.androidx.paging.compose)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.material3.android)
    implementation(libs.androidx.ui.tooling.preview.android)
    implementation(libs.androidx.room.common)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.animation.android)
		debugImplementation(libs.androidx.ui.tooling)
		ksp(libs.androidx.room.compiler)
    implementation(libs.gson)
    implementation(libs.androidx.room.paging)

    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    // Jetpack Compose Integration
    implementation(libs.androidx.hilt.navigation.compose)

    // Room Database with Hilt
    ksp(libs.androidx.room.compiler)

}
