plugins {
    id ("org.jetbrains.kotlin.jvm")
    kotlin("kapt")
}

dependencies {
    // dager hilt
    implementation (DaggerHilt.DAGGER_HILT_JAVAX)
}