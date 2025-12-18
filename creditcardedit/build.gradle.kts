plugins {
    alias(libs.plugins.android.library) // or android.application
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("maven-publish")
}

android {
    namespace = "com.creditcard.library"
    compileSdk = 36

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    publishing {
        // This is required for MavenCentral/JitPack to properly map dependencies
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlin {
        jvmToolchain {
            languageVersion.set(JavaLanguageVersion.of(17))
        }
        jvmToolchain(17)
    }

    buildFeatures {
        compose = true
    }
}

// Move publishing outside afterEvaluate where possible,
// but for Android components, it's often safer inside or using the publishing block above
afterEvaluate {
    publishing {
        publications {
            register<MavenPublication>("release") {
                groupId = "io.github.sujayabraham"
                artifactId = "creditcard-input"
                version = "1.0.0"

                afterEvaluate {
                    from(components["release"])
                }
                // MavenCentral REQUIRES this metadata
                pom {
                    name.set("CreditCardInputField")
                    description.set("A logic-agnostic, configurable credit card input field for Jetpack Compose.")
                    url.set("https://github.com/sujayabraham/CreditSample")
                    licenses {
                        license {
                            name.set("MIT License")
                            url.set("https://opensource.org/licenses/MIT")
                        }
                    }
                    developers {
                        developer {
                            developer {
                                id.set("sujayabraham")
                                name.set("Sujay Abraham")
                            }
                        }
                    }
                    scm {
                        connection.set("scm:git:github.com/sujayabraham/CreditSample.git")
                        developerConnection.set("scm:git:ssh://github.com/sujayabraham/CreditSample.git")
                        url.set("https://github.com/sujayabraham/CreditSample")
                    }
                }
            }
        }

        repositories {
            // For local testing before MavenCentral
            mavenLocal()

            // For GitHub Packages (Alternative to MavenCentral)
            maven {
                name = "GitHubPackages"
                url = uri("https://maven.pkg.github.com/sujayabraham/CreditSample")
                credentials {
                    username = project.findProperty("gpr.user") as String? ?: System.getenv("GPR_USER")
                    password = project.findProperty("gpr.key") as String? ?: System.getenv("GPR_KEY")
                }
            }
        }
    }
}

tasks.register<Zip>("generateRepo") {
    val publishTask = tasks.named(
        "publishReleasePublicationToMyrepoRepository",
        PublishToMavenRepository::class.java
    )
    // Build the repo first, then zip the folder
    dependsOn(publishTask)
    from(layout.buildDirectory.dir("repo"))
    into("creditcard-library")
    archiveFileName.set("creditcard-input-maven.zip")
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.appcompat)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}