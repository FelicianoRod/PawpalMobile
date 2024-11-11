plugins {
    //    alias(libs.plugins.android.application)
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.serialization)
    //    Dagger Hilt
    id("kotlin-kapt")
    //    id("kapt")
    alias(libs.plugins.com.google.dagger.hilt.android)
}

android {
    namespace = "com.example.weight"
    compileSdk = 34

    defaultConfig {
//        applicationId = "com.example.weight"
        minSdk = 24
        targetSdk = 34
//        versionCode = 1
//        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":core"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    // Navegación
    implementation(libs.androidx.navigation.compose)
    // Coil (Imagenes)
    implementation(libs.io.coil.kt.coil)
    //    Icons (Biblioteca de iconos)
    implementation(libs.androidx.compose.material.icons.extended)
    //    Setialization
    implementation(libs.org.jetbrains.kotlinx.serialization.json)
    //   Supabase
    implementation(platform(libs.io.github.jan.tennert.supabase.bom))
    implementation(libs.io.github.jan.tennert.supabase.postgrest.kt)
    implementation(libs.io.github.jan.tennert.supabase.gotrue.kt)
    implementation(libs.io.github.jan.tennert.supabase.realtime.kt)
    implementation(libs.io.github.jan.tennert.supabase.storage.kt)
    //    Dagger Hilt
    implementation(libs.com.google.dagger.hilt.android)
    kapt(libs.com.google.dagger.hilt.android.compiler)
    //    hilt Navigation Compose
    implementation(libs.androidx.hilt.hilt.navigation.compose)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}