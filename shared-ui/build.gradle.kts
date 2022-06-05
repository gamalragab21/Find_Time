import org.jetbrains.compose.compose
plugins {
    kotlin(multiplatform)
    id(androidLib)
    id(composePlugin) version Versions.desktop_compose_plugin
}
android {
// TODO: Add Android Info
    compileSdk = Versions.compile_sdk
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifes t.xml")
            defaultConfig {
        minSdk = Versions.min_sdk
        targetSdk = Versions.target_sdk
    }
            buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}
kotlin {
// TODO: Add Desktop Info
    // 1
    android()
// 2
    jvm("desktop") {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
    }
    sourceSets {
// 3
        val commonMain by getting {
            dependencies {
                implementation(project(":shared"))
                api(compose.foundation)
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)
                api(compose.materialIconsExtended)
                api(compose.ui)
                api(compose.uiTooling)
            }
        }
        val commonTest by getting
        val androidMain by getting {
            dependencies {
                implementation("androidx.appcompat:appcompat:1.3.1")
            }
        }
        val desktopMain by getting
    }
}