plugins {
    id(ApplicationConfig.library)
    id(ApplicationConfig.kotlinAndroid)
    id(ApplicationConfig.kotlinKapt)
    id(ApplicationConfig.hiltAndroid)
}

android {
    compileSdk = ApplicationConfig.compileSdkVersion

    defaultConfig {
        minSdk = ApplicationConfig.minSdk
        targetSdk = ApplicationConfig.targetSdk

        testInstrumentationRunner = ApplicationConfig.androidTestInstrumentation
        consumerProguardFiles(ApplicationConfig.consumerProguardFile)
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = ApplicationConfig.jvmTarget
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(ApplicationModules.core))

    implementation(Dependencies.appLibraries)

    implementation(Dependencies.network)

    implementation(Dependencies.hilt)
    kapt(Dependencies.hilt_compiler)

    testImplementation(Dependencies.testLibraries)
    androidTestImplementation(Dependencies.androidTest)
}
