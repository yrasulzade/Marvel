plugins {
    id(ApplicationConfig.application)
    id(ApplicationConfig.kotlinAndroid)
    id(ApplicationConfig.safeargs)
    id(ApplicationConfig.hiltAndroid)
    id(ApplicationConfig.kotlinKapt)
}

android {
    compileSdk = ApplicationConfig.compileSdkVersion

    defaultConfig {
        applicationId = ApplicationConfig.applicationId
        minSdk = ApplicationConfig.minSdk
        targetSdk = ApplicationConfig.targetSdk
        versionCode = ApplicationConfig.versionCode
        versionName = ApplicationConfig.versionName
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile(ApplicationConfig.proguardOptimize),
                ApplicationConfig.proguardRules
            )
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
    implementation(project(ApplicationModules.marvel_list))
    implementation(project(ApplicationModules.core))

    implementation(Dependencies.appLibraries)

    testImplementation(Dependencies.jUnit)
    androidTestImplementation(Dependencies.androidTest)

    implementation(Dependencies.navigationLibraries)

    implementation(Dependencies.hilt)
    kapt(Dependencies.hilt_compiler)
}
