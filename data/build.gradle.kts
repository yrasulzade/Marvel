plugins{
    id(ApplicationConfig.library)
    id(ApplicationConfig.kotlinAndroid)
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
        debug{
            buildConfigField ("String", "BASE_URL", ApplicationConfig.BASE_URL)
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = ApplicationConfig.jvmTarget
    }
}

dependencies {
    implementation(project(ApplicationModules.domain))

    implementation(Dependencies.appLibraries)

    implementation(Dependencies.koin)

    api(Dependencies.retrofit)
    api(Dependencies.okHttp)
    api(Dependencies.loggingInterceptor)
    api(Dependencies.converterGson)

    implementation(Dependencies.coroutines)

    testImplementation(Dependencies.jUnit)
    androidTestImplementation(Dependencies.androidTest)
}
