plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "com.android.jetpacknews"
        minSdk = 21
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "BASE_URL", "\"https://newsapi.org/\"")
        buildConfigField("String", "API_KEY", properties["NEWS_API_KEY"] as String)
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isDebuggable = true
            isMinifyEnabled = false
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
        kotlinCompilerExtensionVersion = "1.1.0"
    }
    hilt {
        enableAggregatingTask = true
    }
    packagingOptions {
        resources.excludes.add("META-INF/gradle/incremental.annotation.processors")
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.8.0")
    implementation("androidx.compose.ui:ui:${Versions.COMPOSE}")
    implementation("androidx.compose.material:material:${Versions.COMPOSE}")
    implementation("androidx.compose.ui:ui-tooling-preview:${Versions.COMPOSE}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.1")
    implementation("androidx.activity:activity-compose:1.4.0")
    // Hilt
    //dagger
    implementation("com.google.dagger:dagger:${Versions.HILT_AGP}")
    kapt("com.google.dagger:dagger-compiler:${Versions.HILT_AGP}")
    //hilt
    implementation("com.google.dagger:hilt-android:${Versions.HILT_AGP}")
    kapt("com.google.dagger:hilt-compiler:${Versions.HILT_AGP}")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    //Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINE}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINE}")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:${Versions.RETROFIT}")
    implementation("com.squareup.retrofit2:converter-gson:${Versions.RETROFIT}")

    // Coil
    implementation("io.coil-kt:coil-compose:${Versions.COIL}")
    implementation("androidx.core:core-splashscreen:${Versions.SPLASH_API_12V}")

    // Pager
    implementation("com.google.accompanist:accompanist-pager:${Versions.PAGER}")

    implementation("androidx.navigation:navigation-compose:2.5.0-rc02")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.0-rc02")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.0-rc02")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${Versions.COMPOSE}")
    debugImplementation("androidx.compose.ui:ui-tooling:${Versions.COMPOSE}")
    debugImplementation("androidx.compose.ui:ui-test-manifest:${Versions.COMPOSE}")
}
