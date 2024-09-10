plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.beachapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.beachapp"
        minSdk = 24
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

    buildFeatures {
        //noinspection DataBindingWithoutKapt
        dataBinding = true
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.play.services.maps)
    implementation(libs.firebase.database.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)



    implementation(libs.retrofit)
    implementation(libs.gson)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)
    implementation(libs.okhttp)

//    implementation(libs.play.services.auth)

    implementation(platform(libs.firebase.bom))

//
//    // Add the dependency for the Realtime Database library
//    // When using the BoM, you don't specify versions in Firebase library dependencies
//    implementation(libs.firebase.analytics)
//
    implementation(libs.firebase.firestore.ktx)

    implementation(libs.firebase.storage) // Add the latest version of Firebase Storage

    implementation(libs.firebase.auth)

    implementation(libs.ssp.android)
    implementation(libs.sdp.android)

    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")

    implementation(libs.glide) // Use the latest version
    annotationProcessor(libs.compiler)

}