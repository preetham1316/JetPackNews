buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.1.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${Versions.HILT_AGP}")
        classpath("org.jetbrains.kotlin:kotlin-android-extensions:${Versions.KOTLIN}")
    }

    tasks {
        register("clean", Delete::class) {
            delete(rootProject.buildDir)
        }
    }
}