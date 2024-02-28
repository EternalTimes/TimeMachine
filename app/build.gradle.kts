import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.tira.timemachine"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.tira.timemachine"
        minSdk = 31
        targetSdk = 34
        versionName = "0.0.1"

        // Read the version.properties file
        val versionPropertiesFile = rootProject.file("version.properties")
        val versionProperties = Properties()

        // If the file doesn't exist, create it with a versionCode of 1
        if (!versionPropertiesFile.exists()) {
            versionProperties["versionCode"] = "1"
            versionProperties.store(versionPropertiesFile.writer(), null)
        } else {
            // If the file exists, load it and increment the versionCode
            versionProperties.load(versionPropertiesFile.reader())
            val versionCode = versionProperties["versionCode"].toString().toInt()
            versionProperties["versionCode"] = (versionCode + 1).toString()
            versionProperties.store(versionPropertiesFile.writer(), null)
        }

        // Set the versionCode in the build file
        versionCode = versionProperties["versionCode"].toString().toInt()


        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        versionNameSuffix = "Neutronium"
        multiDexEnabled = true
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
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

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    implementation(libs.androidx.ui.test.manifest)
}