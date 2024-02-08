plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    // Add the Google services Gradle plugin
    id("com.google.gms.google-services")
    //Figma plugin
    id ("com.google.relay") version "0.3.09"

}

android {
    namespace = "com.raq.motori.android.customerapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.raq.motori.android.customerapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        //UAE PASS START -- Adding Custom Scheme Variables
        buildConfigField ("String", "URI_SCHEME", "\"com.raq.motori.android.customerapp\"") // Change to your app name or any custom scheme. Donot use uaepasssample
        buildConfigField ("String", "URI_HOST_SUCCESS", "\"success\"")
        buildConfigField ("String", "URI_HOST_FAILURE", "\"failure\"")
        buildConfigField ("String", "CLIENT_ID", "\"sandbox_stage\"") // Change the client id to the one provided to you.
        buildConfigField ("String", "CLIENT_SECRET", "\"sandbox_stage\"") // Change the client secret to the one provided to you.
        buildConfigField ("String", "REDIRECT_URL", "\"https://stg-selfcare.uaepass.ae/\"") // Change the redirect url to the one provided to you.
        buildConfigField ("Integer", "ENVIRONMENT", "1") // Change the environment to 0, 1 or 2. 0 is for qa, 1 is for staging and 2 is for production.

        manifestPlaceholders["host_success"] = "success"
        manifestPlaceholders["host_failure"] = "failure"
        manifestPlaceholders["scheme"] = "com.raq.motori.android.customerapp"

        //UAE PASS END -- Adding Custom Scheme Variables
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
        buildConfig = true
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

// Allow references to generated code
kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar", "*.aar"))))

    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    // compose navigation
    val navVersion = "2.7.6"
    implementation("androidx.navigation:navigation-compose:$navVersion")
    implementation("androidx.hilt:hilt-navigation:1.1.0")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")

    //Hilt
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")

    // Networking Retrofit & Coroutine
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.2")
    implementation ("com.squareup.retrofit2:converter-scalars:2.9.0")

    // COIL for image loading
    implementation("io.coil-kt:coil-compose:2.5.0")
    implementation("io.coil-kt:coil-svg:2.5.0")

    //bottom navigation bar
    implementation("androidx.compose.material:material:1.5.4")

    //Firebase Analytics
    implementation(platform("com.google.firebase:firebase-bom:32.7.1"))
    implementation("com.google.firebase:firebase-analytics")

    //Mockk
    testImplementation ("io.mockk:mockk:1.12.4")

    // Kotlin extensions for androidx.test.core
    androidTestImplementation("androidx.test:core-ktx:1.5.0")

    //For runBlockingTest, CoroutineDispatcher etc.
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.4.2")
//For InstantTaskExecutorRule
    testImplementation ("androidx.arch.core:core-testing:2.1.0")


    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.0")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}