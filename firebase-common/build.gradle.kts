plugins {
    id("com.android.library")
    kotlin("multiplatform")
    `maven-publish`
}
repositories {
    mavenCentral()
    google()
}
version = "0.1.0"

android {
    compileSdkVersion(property("targetSdkVersion") as Int)
    defaultConfig {
        minSdkVersion(property("minSdkVersion") as Int)
        targetSdkVersion(property("targetSdkVersion") as Int)
    }
    sourceSets {
        getByName("main") {
            manifest.srcFile("src/androidMain/AndroidManifest.xml")
        }
    }
}

kotlin {
    js {
        val main by compilations.getting {
            kotlinOptions {
                moduleKind = "commonjs"
            }
        }
    }
    android {
        publishLibraryVariants("release", "debug")
    }
    jvm {
        val main by compilations.getting {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    sourceSets {
        val androidMain by getting {
            dependencies {
                api("com.google.firebase:firebase-common:17.1.0")
            }
        }
        val jsMain by getting {
            dependencies {
//                implementation(npm("firebase", "6.2.3"))
            }
        }
        val jvmMain by getting {
            kotlin.srcDir("src/androidMain/kotlin")
        }
    }
}
