plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
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
    implementation (project (":domain"))
    implementation (KTX.CORE)
    implementation (AndroidX.APP_COMPAT)

    // ViewModel
    implementation(AndroidX.LIFECYCLE_VIEW_MODEL)

    //by viewModel
    implementation(AndroidX.ACTIVITY)
    implementation(AndroidX.FRAGMENT)

    // ViewModel
    implementation(AndroidX.LIFECYCLE_VIEW_MODEL)

    // Retrofit
    implementation(Retrofit.RETROFIT)
    implementation(Retrofit.CONVERTER_GSON)
    implementation(Retrofit.CONVERTER_JAXB)

    //okHttp
    implementation(OkHttp.OKHTTP)
    implementation(OkHttp.LOGGING_INTERCEPTOR)

    //Glide
    implementation(Glide.GLIDE)
    annotationProcessor(Glide.GLIDE_ANNOTATION)

    //Gson
    implementation(Gson.GSON)


    // dager hilt
    implementation (DaggerHilt.DAGGER_HILT)
    kapt (DaggerHilt.DAGGER_HILT_COMPILER)
    kapt (DaggerHilt.DAGGER_HILT_ANDROIDX_COMPILER)

}