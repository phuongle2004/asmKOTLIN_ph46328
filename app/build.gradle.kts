plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.gms.google-services")

}

android {
    namespace = "com.kotlin.asmkotlin_ph46328"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.kotlin.asmkotlin_ph46328"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
    // Cấu hình các thư viện cơ bản
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    // Cấu hình cho Jetpack Compose
    implementation(platform(libs.androidx.compose.bom)) // BOM để quản lý các phiên bản Compose
    implementation(libs.androidx.ui) // Có thể là androidx.compose.ui
    implementation(libs.androidx.ui.graphics) // Có thể là androidx.compose.ui.graphics
    implementation(libs.androidx.ui.tooling.preview) // Có thể là androidx.compose.ui.tooling.preview
    implementation(libs.androidx.material3) // Nếu bạn sử dụng Material3

    // Thư viện cho testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom)) // Cho testing Compose
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:33.4.0")) // BOM Firebase
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-auth-ktx:23.0.0") // Sử dụng phiên bản mới nhất
    implementation("com.google.android.gms:play-services-auth:21.2.0") // Firebase Auth

    // Navigation Compose
    implementation("androidx.navigation:navigation-compose:2.8.2") // Phiên bản mới nhất
    implementation("androidx.activity:activity-compose:1.7.2") // Đảm bảo sử dụng phiên bản mới nhất

    // Material3, nếu bạn đã thêm vào BOM thì không cần thêm ở đây
    implementation("androidx.compose.material3:material3:1.3.0") // Sử dụng nếu cần



    // Thư viện Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")

    // Converter để chuyển JSON thành đối tượng Kotlin
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    // Thêm OkHttp (thư viện HTTP client)
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.3")
    implementation("io.coil-kt:coil-compose:2.6.0")



}
