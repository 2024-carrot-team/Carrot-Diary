import SdkVersions.compileSdk
import SdkVersions.minSdk
import SdkVersions.targetSdk

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.carrot.carrotdiary"
    compileSdk = SdkVersions.compileSdk

    defaultConfig {
        applicationId = "com.carrot.carrotdiary"
        minSdk = SdkVersions.minSdk
        targetSdk = SdkVersions.targetSdk
        versionCode = AppVersions.androidVersionCode
        versionName = AppVersions.androidVersionName

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    //androidx
    implementation(KTX.CORE)
    implementation(AndroidX.APP_COMPAT)
    implementation(AndroidX.MATERIAL)
    implementation(AndroidX.CONSTRAINT_LAYOUT)

    implementation(AndroidX.RECYCLERVIEW)

    //nav component
    implementation(NavComponent.NAVIGATION_FRAGMENT)
    implementation(NavComponent.NAVIGATION_UI)
    implementation(NavComponent.NAVIGATION_DYNAMIC_FEATURES_FRAGMENT)
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    androidTestImplementation(NavComponent.NAVIGATION_TESTING)
    implementation(NavComponent.NAVIGATION_COMPOSE)

    // ViewModel
    implementation(AndroidX.LIFECYCLE_VIEW_MODEL)

    //by viewModel
    implementation(AndroidX.ACTIVITY)
    implementation(AndroidX.FRAGMENT)

    // ViewModel
    implementation(AndroidX.LIFECYCLE_VIEW_MODEL)


    //Glide
    implementation("com.github.bumptech.glide:glide:4.16.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.9.0")


    //Gson
    implementation(Gson.GSON)
}