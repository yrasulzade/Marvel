pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()

        resolutionStrategy {
            eachPlugin {
                if(requested.id.id == "dagger.hilt.android.plugin") {
                    useModule("com.google.dagger:hilt-android-gradle-plugin:2.42")
                }
            }
        }
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Marvel"
include (":app")
include (":features:details")
include (":features:marvel-list")
include (":core")
include (":data")
include (":domain")
include (":features")
