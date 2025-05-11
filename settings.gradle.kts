rootProject.name = "Casper-Feed"

pluginManagement {
    includeBuild("casper-convention")
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

