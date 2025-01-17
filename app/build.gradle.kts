plugins {
    id("com.android.application")
    id("kotlin-android")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
}

kotlin {
    sourceSets {
        debug {
            kotlin.srcDir("build/generated/ksp/debug/kotlin")
        }
        release {
            kotlin.srcDir("build/generated/ksp/release/kotlin")
        }
    }
}

android {
    namespace = "com.example.noteapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.noteapp"
        minSdk = 23
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    packaging {
        resources {
            excludes += setOf("META-INF/atomicfu.kotlin_module")
        }
    }

}

dependencies {

    // Rounded Image View
    implementation("com.makeramen:roundedimageview:2.3.0")

    // Easy Permission
    implementation("pub.devrel:easypermissions:3.0.0")

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.activity:activity-ktx:1.9.0")
    implementation("androidx.fragment:fragment-ktx:1.7.1")

    // Room components
    implementation("androidx.room:room-ktx:2.6.1")
    implementation("com.google.firebase:firebase-auth:23.0.0")
    implementation("com.google.firebase:firebase-database:21.0.0")
    ksp("androidx.room:room-compiler:2.6.1")
    androidTestImplementation("androidx.room:room-testing:2.6.1")
    implementation(platform("com.google.firebase:firebase-bom:33.1.1"))
    implementation("com.google.firebase:firebase-analytics")

    // Lifecycle components
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.0")
    implementation("androidx.lifecycle:lifecycle-common-java8:2.8.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:r2.6.2")

    // Kotlin components
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.0")

    // UI
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.android.material:material:1.12.0")
    implementation ("com.google.android.gms:play-services-auth:21.2.0")

    // Sdp
    implementation("com.intuit.sdp:sdp-android:1.1.1")

    // Dagger Hilt
    implementation("com.google.dagger:hilt-android:2.51.1")
    ksp("com.google.dagger:hilt-android-compiler:2.51.1")

    // Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.arch.core:core-testing:2.2.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.1.0") {
        exclude(group = "com.android.support", module = "support-annotations")
    }
    androidTestImplementation("androidx.test.ext:junit:1.1.5")

}