import java.io.FileInputStream
import java.util.Properties

// Leer propiedades del archivo local
val properties = Properties().apply {
    load(FileInputStream(rootProject.file("local.properties")))
}

plugins {
//    alias(libs.plugins.android.application)
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.example.authentication"
    compileSdk = 34

    val supabaseKey: String = properties.getProperty("supabaseKey")
    val supabaseUrl: String = properties.getProperty("supabaseUrl")

    // Habilitar la generación de BuildConfig
    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
//        applicationId = "com.example.authentication"
        minSdk = 24
        targetSdk = 34
//        versionCode = 1
//        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField("String", "supabaseKey", "\"$supabaseKey\"")
        buildConfigField("String", "supabaseUrl", "\"$supabaseUrl\"")

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

    implementation(libs.androidx.navigation.compose)
    val ktorClientAndroidVersion: String = properties.getProperty("ktorClientAndroidVersion")
    val lifecycleViewmodelVersion: String = properties.getProperty("lifecycleViewmodelVersion")
//    val gotruektVersion: String = properties.getProperty("gotrue-ktVersion")


//    implementation(platform("io.github.jan-tennert.supabase:bom:2.5.3"))
//    implementation("io.github.jan-tennert.supabase:postgrest-kt")
//    implementation("io.github.jan-tennert.supabase:realtime-kt")


//    implementation("io.github.jan-tennert.supabase:gotrue-kt:$gotrueVersion")
    implementation("io.github.jan-tennert.supabase:gotrue-kt:2.5.3")

//    implementation("io.ktor:ktor-client-android:$ktorClientAndroidVersion")
    implementation("io.ktor:ktor-client-cio:2.3.12")
    implementation("androidx.lifecycle:lifecycle-viewmodel:2.8.4")

    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    implementation ("androidx.compose.runtime:runtime-livedata:1.2.1")

    implementation("androidx.webkit:webkit:1.11.0")
    implementation("androidx.compose.ui:ui-viewbinding")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}