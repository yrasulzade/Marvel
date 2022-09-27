import org.gradle.api.artifacts.ProjectDependency
import org.gradle.api.artifacts.dsl.DependencyHandler

object Dependencies {
    private const val coreKtx = "androidx.core:core-ktx:${ApplicationVersions.coreKtx}"
    private const val appcompat = "androidx.appcompat:appcompat:${ApplicationVersions.appcompat}"
    private const val material = "com.google.android.material:material:${ApplicationVersions.material}"
    private const val constraintLayout = "androidx.constraintlayout:constraintlayout:${ApplicationVersions.constraintLayout}"

    private const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${ApplicationVersions.navigation}"
    private const val navigationUi = "androidx.navigation:navigation-ui-ktx:${ApplicationVersions.navigation}"

    const val hilt = "com.google.dagger:hilt-android:${ApplicationVersions.hilt}"
    const val hilt_compiler = "com.google.dagger:hilt-compiler:${ApplicationVersions.hilt_compiler}"

    const val jUnit = "junit:junit:${ApplicationVersions.jUnit}"
    private const val mockitoCore = "org.mockito:mockito-core:${ApplicationVersions.mockitoCore}"
    private const val mockk = "io.mockk:mockk:${ApplicationVersions.mockk}"
    private const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${ApplicationVersions.coroutinesTest}"
    private const val coreTesting = "androidx.arch.core:core-testing:${ApplicationVersions.coreTesting}"
    const val extJunit = "androidx.test.ext:junit:${ApplicationVersions.extJunit}"
    const val espresso = "androidx.test.espresso:espresso-core:${ApplicationVersions.espresso}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${ApplicationVersions.retrofit}"
    private const val okHttp = "com.squareup.okhttp3:okhttp:${ApplicationVersions.okHttp}"
    private const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${ApplicationVersions.okHttp}"
    private const val converterGson = "com.squareup.retrofit2:converter-gson:${ApplicationVersions.retrofit}"

    private const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${ApplicationVersions.coroutinesCore}"
    private const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${ApplicationVersions.coroutinesAndroid}"
    private const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${ApplicationVersions.lifecycleViewModel}"

    const val coil = "io.coil-kt:coil:${ApplicationVersions.coil}"

    val appLibraries = arrayListOf<String>().apply {
        add(coreKtx)
        add(appcompat)
        add(constraintLayout)
        add(material)
    }

    val navigationLibraries = arrayListOf<String>().apply {
        add(navigationFragment)
        add(navigationUi)
    }

    val testLibraries = arrayListOf<String>().apply {
        add(jUnit)
        add(mockitoCore)
        add(mockk)
        add(coroutinesTest)
        add(coreTesting)
    }

    val androidTest = arrayListOf<String>().apply {
        add(extJunit)
        add(espresso)
    }

    val network = arrayListOf<String>().apply {
        add(retrofit)
        add(okHttp)
        add(loggingInterceptor)
        add(converterGson)
    }

    val coroutines = arrayListOf<String>().apply {
        add(coroutinesCore)
        add(coroutinesAndroid)
        add(lifecycleViewModel)
    }
}

//util functions for adding the different type dependencies from build.gradle file
fun DependencyHandler.kapt(list: List<String>) {
    list.forEach { dependency ->
        add("kapt", dependency)
    }
}

fun DependencyHandler.kapt(libPath: String) {
    add("kapt", libPath)
}

fun DependencyHandler.implementation(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}

fun DependencyHandler.implementation(lib: String) {
    add("implementation", lib)
}

private fun DependencyHandler.implementation(project: ProjectDependency) {
    add("implementation", project)
}

fun DependencyHandler.androidTestImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("androidTestImplementation", dependency)
    }
}

fun DependencyHandler.testImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("testImplementation", dependency)
    }
}
