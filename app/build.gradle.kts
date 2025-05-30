plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.granatv1"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.granatv1"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

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
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))  // Для установки версии Compose
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.runtime.android)

    // Удаляем дублирующиеся зависимости
    implementation("androidx.media:media:1.6.0")  // Зависимость для MediaSession и MediaMetadata
    implementation("androidx.core:core-ktx:1.8.0") // Уже включена через libs
    implementation("androidx.compose.ui:ui:1.4.0")  // Версия UI Compose
    implementation("androidx.compose.material3:material3:1.0.0")

    // Убираем повторяющиеся зависимости
    implementation("androidx.navigation:navigation-compose:2.8.9") // Новая версия
    implementation("com.google.accompanist:accompanist-navigation-animation:0.34.0")
    implementation("androidx.core:core-splashscreen:1.0.1")

    // Тестовые зависимости
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
