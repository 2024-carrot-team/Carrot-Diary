import Versions.NAV_VERSION

object Versions {
    const val NAV_VERSION = "2.4.0-alpha10"
}

object Retrofit {
    const val RETROFIT = "com.squareup.retrofit2:retrofit:2.9.0"
    const val CONVERTER_GSON = "com.squareup.retrofit2:converter-gson:2.9.0"
    const val CONVERTER_JAXB = "com.squareup.retrofit2:converter-jaxb:2.9.0"
    const val CONVERTER_SCALARS = "com.squareup.retrofit2:converter-scalars:2.9.0"
}

object Glide {
    const val GLIDE = "com.github.bumptech.glide:glide:4.16.0"
    const val GLIDE_ANNOTATION = "com.github.bumptech.glide:compiler:4.9.0"

}


object OkHttp {
    const val OKHTTP = "com.squareup.okhttp3:okhttp:4.9.1"
    const val LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:4.9.1"
}

object NavComponent {
    const val NAVIGATION_FRAGMENT = "androidx.navigation:navigation-fragment-ktx:$NAV_VERSION"
    const val NAVIGATION_UI = "androidx.navigation:navigation-ui-ktx:$NAV_VERSION"
    const val NAVIGATION_DYNAMIC_FEATURES_FRAGMENT =
        "androidx.navigation:navigation-dynamic-features-fragment:$NAV_VERSION"
    const val NAVIGATION_TESTING = "androidx.navigation:navigation-testing:$NAV_VERSION"
    const val NAVIGATION_COMPOSE = "androidx.navigation:navigation-compose:2.4.0-alpha10"
}

object AndroidX {
    const val MATERIAL = "androidx.compose.material:material:1.0.0-rc02"
    const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:2.1.4"
    const val APP_COMPAT = "androidx.appcompat:appcompat:1.6.1"
    const val LIFECYCLE_VIEW_MODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0"
    const val LIFECYCLE_LIVEDATA = "androidx.lifecycle:lifecycle-livedata-ktx:2.3.1"
    const val ACTIVITY = "androidx.activity:activity-ktx:1.3.1"
    const val FRAGMENT = "androidx.fragment:fragment-ktx:1.5.2"
    const val RECYCLERVIEW = "androidx.recyclerview:recyclerview:1.2.1"
}

object KTX {
    const val CORE = "androidx.core:core-ktx:1.6.0"
}

object Gson {
    const val GSON = "com.google.code.gson:gson:2.8.9"
}


object DaggerHilt {
    const val DAGGER_HILT_VERSION = "2.42"
    const val DAGGER_HILT = "com.google.dagger:hilt-android:2.42"
    const val DAGGER_HILT_COMPILER = "com.google.dagger:hilt-android-compiler:2.42"
    const val DAGGER_HILT_ANDROIDX_COMPILER = "androidx.hilt:hilt-compiler:1.0.0"
    const val DAGGER_HILT_JAVAX = "javax.inject:javax.inject:1"
}

object TestTool {
    const val JUNIT = "junit:junit:4.+"
    const val ANDROID_X_JUNIT = "androidx.test.ext:junit:1.1.3"
    const val ANDROID_X_ESPRESSO = "androidx.test.espresso:espresso-core:3.4.0"
}
