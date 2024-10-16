plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.too_do_list"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.too_do_list"
        minSdk = 21
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

        implementation ("androidx.appcompat:appcompat:1.5.1")
        implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
        implementation ("androidx.recyclerview:recyclerview:1.2.1")
        implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
        implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
        implementation ("com.google.android.material:material:1.8.0")
        implementation ("androidx.room:room-runtime:2.5.0")
        annotationProcessor ("androidx.room:room-compiler:2.5.0")
    }


