plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.movieapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.movieapplication"
        minSdk = 24
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf(
                    "room.schemaLocation" to "$projectDir/schemas",
                    "room.incremental" to "true"
                )
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
            buildConfigField("String", "API_KEY", "\"9b3efc88fed3cb0e25a0849788f05166\"")
        }
        debug {
            buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
            buildConfigField("String", "API_KEY", "\"9b3efc88fed3cb0e25a0849788f05166\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    val paging_version = "3.2.0"
    val room_version = "2.5.2"

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.1")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("com.google.dagger:hilt-android:2.47")
    ksp("com.google.dagger:hilt-android-compiler:2.47")

    implementation("androidx.paging:paging-runtime:$paging_version")
    testImplementation("androidx.paging:paging-common:$paging_version")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")

    //coroutine
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")

    implementation("androidx.room:room-runtime:$room_version")
    ksp("androidx.room:room-compiler:2.5.2")

    implementation("com.github.bumptech.glide:glide:4.15.1")
    implementation("com.facebook.shimmer:shimmer:0.5.0")
    implementation("com.pierfrancescosoffritti.androidyoutubeplayer:core:12.1.0")
}