import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.4.2"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.4.21"
    kotlin("plugin.spring") version "1.4.21"
    kotlin("plugin.jpa") version "1.4.21"
    kotlin("plugin.allopen") version "1.4.21"
}

allOpen {
    annotation("javax.persistence.Entity")
}

group = "se.newton"
version = "0.0.1-SNAPSHOT"
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(15))
    }
}

sourceSets {
    create("integrationTest") {
        java.srcDirs("src/testIntegration/kotlin", "src/testIntegration/java")
        resources.srcDir("src/testIntegration/resources")
        compileClasspath += sourceSets.main.get().output
        runtimeClasspath += sourceSets.main.get().output
    }

    create("unitTest") {
        java.srcDirs("src/test/kotlin", "src/test/java")
        resources.srcDir("src/test/resources")
        compileClasspath += sourceSets.main.get().output
        runtimeClasspath += sourceSets.main.get().output
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }

    this["integrationTestImplementation"].extendsFrom(configurations["testImplementation"])
    this["integrationTestRuntime"].extendsFrom(configurations["testRuntime"])
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    compileOnly("org.projectlombok:lombok")
    runtimeOnly("org.postgresql:postgresql")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("com.h2database:h2")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "15"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

// Create test integrationTest and unitTest suite
fun createTestTask(taskName: String, sourceSetName: String) {
    tasks.create<Test>(taskName) {
        testClassesDirs = sourceSets[sourceSetName].output.classesDirs
        classpath = sourceSets[sourceSetName].runtimeClasspath
    }
}
createTestTask("integrationTest", "integrationTest")
createTestTask("unitTest", "test")

// Make the test task run both sets
tasks.test {
    dependsOn(tasks["integrationTest"])
}