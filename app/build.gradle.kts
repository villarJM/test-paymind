import io.github.klahap.dotenv.DotEnvBuilder

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.devvillar.testpaymind"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.devvillar.testpaymind"
        minSdk = 27
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            val env = DotEnvBuilder.dotEnv {
                addFile("$rootDir/.env.dev")
                addSystemEnv()
            }
            isDebuggable = true
            applicationIdSuffix = ".debug"
            buildConfigField("String", "BASE_URL", "\"${env["BASE_URL"]}\"")
            buildConfigField("boolean", "IS_PRODUCTION", "${env["DEBUG_MODE"]}")
        }
        release {
            isMinifyEnabled = false
            val env = DotEnvBuilder.dotEnv {
                addFile("$rootDir/.env.prod")
                addSystemEnv()
            }
            isDebuggable = false
            buildConfigField("String", "BASE_URL", "\"${env["BASE_URL"]}\"")
            buildConfigField("boolean", "IS_PRODUCTION", "${env["DEBUG_MODE"]}")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}