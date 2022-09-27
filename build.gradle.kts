plugins {
    id(ApplicationConfig.application) version ApplicationVersions.applicationPlugin apply false
    id(ApplicationConfig.androidLib) version ApplicationVersions.androidLibVersion apply false
    id(ApplicationConfig.jetbrainsKotlin) version ApplicationVersions.jetbrainsKotlin apply false
    id(ApplicationConfig.hiltAndroid) version ApplicationVersions.hilt apply false
    id(ApplicationConfig.kotlinJvm) version ApplicationVersions.kotlinJvm apply false
    id(ApplicationConfig.safeargs) version ApplicationVersions.safeargs apply false
}

task<Delete>("clean"){
    delete = setOf(rootProject.buildDir)
}
