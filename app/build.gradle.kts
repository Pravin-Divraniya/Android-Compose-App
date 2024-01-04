plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.composeapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.composeapp"
        minSdk = 28
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
        kotlinCompilerExtensionVersion = "1.5.7"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes.add("/META-INF/gradle/*")
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    //Hilt
    implementation(libs.android.hilt)
    kapt(libs.android.hilt.compiler)
    implementation(libs.hilt.navigation.compose)

    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson.convertor)
    implementation(libs.okhttp3.interceptor)

    //Coil
    implementation(libs.coil)

    //Compose lifecycle
    implementation(libs.androidx.lifecycle.compose)

    //Paging
    implementation(libs.androidx.paging)
    implementation(libs.androidx.paging.compose)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

kapt{
    correctErrorTypes = true
}