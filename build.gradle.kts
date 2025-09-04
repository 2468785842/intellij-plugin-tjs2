plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "2.1.0"
    id("org.jetbrains.intellij.platform") version "2.7.1"
    id("antlr")
}

group = "org.github"
version = "1.0.0"

sourceSets {
    main {
        java {
            srcDirs("src/main/gen", "src/main/kotlin")
        }
        resources {
            srcDirs("src/main/resources")
        }
    }
}

repositories {
    mavenCentral()
    intellijPlatform {
        defaultRepositories()
    }
}

// Configure IntelliJ Platform Gradle Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-intellij-platform-gradle-plugin.html
dependencies {
    antlr("org.antlr:antlr4:4.13.2") { // use ANTLR version 4
        exclude("com.ibm.icu", "icu4j")
    }

    implementation("org.antlr:antlr4-intellij-adaptor:0.1")

    intellijPlatform {
        create("IC", "2025.1.4.1")
        plugin("PsiViewer:2025.1")
        testFramework(org.jetbrains.intellij.platform.gradle.TestFrameworkType.Platform)

        // Add necessary plugin dependencies for compilation here, example:
        // bundledPlugin("com.intellij.java")
    }
}

intellijPlatform {
    pluginConfiguration {
        ideaVersion {
            sinceBuild = "251"
        }

        changeNotes = """
            Initial version
        """.trimIndent()
    }
}

tasks {

    generateGrammarSource {
        include("**/TJS2*.g4")

        arguments.add("-package")
        arguments.add("org.github.tjs2")
        arguments.add("-Xexact-output-dir")
        outputDirectory = file("$projectDir/src/main/gen/org/github/tjs2")
    }

    compileKotlin {
        dependsOn(generateGrammarSource)
    }

    // Set the JVM compatibility versionÍs
    withType<JavaCompile> {
        sourceCompatibility = "21"
        targetCompatibility = "21"
    }

    withType<Copy> {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }

}

kotlin {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21)
    }
}
