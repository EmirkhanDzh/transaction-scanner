import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.6.6"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.spring") version "1.6.10"
    kotlin("plugin.jpa") version "1.6.10"
}

group = "com.notiprice"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()

}

dependencies {
    // Spring
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    // Scarp and telegram
    implementation("org.telegram:telegrambots-spring-boot-starter:6.0.1")
    implementation("org.jsoup:jsoup:1.14.3")
    implementation("us.codecraft:xsoup:0.3.2")
    // Logging
    implementation("io.github.microutils:kotlin-logging-jvm:2.1.21")
    implementation("ch.qos.logback:logback-classic:1.2.11")
    // Coroutine
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")
    // Databases
    runtimeOnly("com.h2database:h2")
    runtimeOnly("org.postgresql:postgresql")


    // Testing
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}
tasks.withType<Test> {
    useJUnitPlatform()
}
