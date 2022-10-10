plugins{
    id(ApplicationConfig.library)
    id(ApplicationConfig.kotlinAndroid)
    id(ApplicationConfig.safeargs)
//    id(ApplicationConfig.hiltAndroid)
    id(ApplicationConfig.kotlinKapt)
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
    implementation(project(ApplicationModules.data))
    implementation(project(ApplicationModules.domain))

    implementation(project(ApplicationModules.core))

    implementation(Dependencies.appLibraries)

    implementation(Dependencies.retrofit)

    implementation(Dependencies.navigationLibraries)

    implementation(Dependencies.coil)

//    implementation(Dependencies.hilt)
//    kapt(Dependencies.hilt_compiler)

    implementation(Dependencies.koin)

    testImplementation(Dependencies.testLibraries)
    androidTestImplementation(Dependencies.androidTest)
}
