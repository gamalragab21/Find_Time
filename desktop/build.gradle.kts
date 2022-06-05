import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
// 1
plugins {
    kotlin(multiplatform)
    id(composePlugin) version Versions.desktop_compose_plugin
}
// 2
group = "com.example.findtime"
version = "1.0.0"
// 3
kotlin {
// TODO: Add Kotlin
    // 1
    jvm {
        withJava()
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
    }
// 2
    sourceSets {
        val jvmMain by getting {
// 3
            kotlin.srcDirs("src/jvmMain/kotlin")
            dependencies {
// 4
                implementation(compose.desktop.currentOs)
// 5
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)
                api(compose.ui)
                api(compose.materialIconsExtended)
                implementation(Deps.napier)
// Coroutines
                implementation(Deps.Coroutines.common)
// 6
                implementation(project(":shared"))
                implementation(project(":shared-ui"))
            }
        }
    }
}
// TODO: Add Compose Desktop
// 1
compose.desktop {
// 2
    application {
// 3
        mainClass = "MainKt"
// 4
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi,
                TargetFormat.Deb)
            packageName = "FindTime"
            macOS {
                bundleID = "com.raywenderlich.findtime"
            }
        }
    }
}