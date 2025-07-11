rootProject.name = "Casper-Feed"

pluginManagement {
    includeBuild("casper-convention")
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

